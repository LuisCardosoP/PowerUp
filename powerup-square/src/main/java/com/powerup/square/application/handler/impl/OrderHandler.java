package com.powerup.square.application.handler.impl;

import com.google.gson.Gson;
import com.powerup.square.application.dto.*;
import com.powerup.square.application.handler.IOrderHandler;
import com.powerup.square.application.mapper.IOrderRequestMapper;
import com.powerup.square.application.mapper.IOrderResponseMapper;
import com.powerup.square.domain.api.*;
import com.powerup.square.domain.exception.*;

import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.model.OrderPlates;
import com.powerup.square.domain.spi.IPlatePersistencePort;
import com.powerup.square.infraestructure.configuration.TwilioConfiguration;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.lang.Math;
@Service
@Transactional

public class OrderHandler implements IOrderHandler {
    private final IEmployeeServicePort iEmployeeServicePort;
    private final IOrderServicePort iOrderServicePort;

    private final IOrderRequestMapper iOrderRequestMapper;
    private final IOrderResponseMapper iOrderResponseMapper;

    private final IOrderPlatesServicePort iOrderPlatesServicePort;

    private final IRestaurantServicePort iRestaurantServicePort;

    private final IPlatePersistencePort iPlatePersistencePort;
    private final IPlateServicePort iPlateServicePort;

    private final TwilioConfiguration twilioConfiguration;

    public OrderHandler(IEmployeeServicePort iEmployeeServicePort, IOrderServicePort iOrderServicePort, IOrderRequestMapper iOrderRequestMapper, IOrderResponseMapper iOrderResponseMapper, IOrderPlatesServicePort iOrderPlatesServicePort, IRestaurantServicePort iRestaurantServicePort, IPlatePersistencePort iPlatePersistencePort, IPlateServicePort iPlateServicePort, TwilioConfiguration twilioConfiguration) {
        this.iEmployeeServicePort = iEmployeeServicePort;
        this.iOrderServicePort = iOrderServicePort;
        this.iOrderRequestMapper = iOrderRequestMapper;
        this.iOrderResponseMapper = iOrderResponseMapper;
        this.iOrderPlatesServicePort = iOrderPlatesServicePort;
        this.iRestaurantServicePort = iRestaurantServicePort;
        this.iPlatePersistencePort = iPlatePersistencePort;
        this.iPlateServicePort = iPlateServicePort;
        this.twilioConfiguration = twilioConfiguration;
    }


    @Override
    public void updateOrderAssign(OrderUpdateStateRequest orderUpdateStateRequest) {
        List<Order> orderUpdated = new ArrayList<>();
        /*for(Long idOrder: orderUpdateStateRequest.getIdOrders()) {
            Order order = iOrderServicePort.getOrderById(idOrder);
            order.setState("Preparando");

            order.setIdEmployee(orderUpdateStateRequest.getIdEmployee());

            orderUpdated.add(order);
        }

        iOrderServicePort.updateOrderAssign(orderUpdated);*/
        for(Long idOrder: orderUpdateStateRequest.getIdOrders()) {
            Order order = iOrderServicePort.getOrderById(idOrder);

            //If an order was already assigned, the process will fail
            if(order.getState().equals("Preparando")){
                throw new OrderAssignedAlreadyException();
            }
            order.setState("Preparando");
            order.setIdEmployee(orderUpdateStateRequest.getIdEmployee());

            orderUpdated.add(order);
        }

        iOrderServicePort.updateOrderAssign(orderUpdated);
    }

    @Override
    public List<OrderResponse> getAllOrdersByState(int page, int size, OrderState orderState) {
//        Long restaurantOfTheEmployee = iEmployeeServicePort.getEmployee(orderState.getIdEmployee()).getIdRestaurant();
        return iOrderResponseMapper.toOrderResponseList(iOrderServicePort.getAllOrdersByState(page, size, orderState));
    }

    @Override
    public void saveOrder(OrderRequest orderRequest) {
        Order order = iOrderRequestMapper.toOrder(orderRequest);

           // save order cliente
        if(iOrderServicePort.existsByIdClient(order.getIdClient())) {

            // validar si el cliente ya tiene un pedido
            if(iOrderServicePort.getOrderByIdClient(order.getIdClient()).getState() != "save") {
                throw new PendingExistsException();
            }
        }


        // Validar si el id del plato corresponde al restaurante
       for(int x = 0; x<=orderRequest.getIdPlates().size()-1;x++) {
            if(iPlateServicePort.getPlate(orderRequest.getIdPlates().get(x)).getRestaurant().getId() !=
                    orderRequest.getIdRestaurant()) {
                throw new PlateIsNotRestaurantException();
            }
        }


        Date date = new java.util.Date();

        order.setId(-1L);
        order.setDate(date);
        order.setState("Pendiente");
        order.setRestaurant(iRestaurantServicePort.getRestaurant(orderRequest.getIdRestaurant()));

        // Saving data in orders table
        iOrderServicePort.saveOrder(order);
        Order newOrder = iOrderServicePort.getOrderByIdClient(order.getIdClient());


        List<OrderPlates> listOrderPlates = new ArrayList<>();

        for(int x = 0; x<=orderRequest.getIdPlates().size()-1; x++){
            OrderPlates orderPlates = new OrderPlates(
                    newOrder,
                    iPlatePersistencePort.getPlate(orderRequest.getIdPlates().get(x)),
                    orderRequest.getAmountPlates().get(x)
            );
            listOrderPlates.add(orderPlates);
        }

        //Saving data in order_plates table
        iOrderPlatesServicePort.saveOrderPlates(listOrderPlates);

    }

    @Override
    public void OrderOkNotify(OrderOkRequest orderOkRequest) {

        Order order = iOrderServicePort.getOrderByIdClient(orderOkRequest.getIdClient());
        order.setState("Listo");

        iOrderServicePort.saveOrder(order);

        Double randomPin = Math.random() * (10000 - orderOkRequest.getIdClient()+1);
        String bodySms = "\n\nHola! tu orden ya está lista.\n\n Recuerda tu PIN::\n\n"+Math.round(randomPin);
        twilioConfiguration.sendSMS(bodySms);


    }

    @Override
    public List<OrderPlates> getOrderPlatesById(Long id) {
        return iOrderPlatesServicePort.getAllOrderPlatesByOrderId(id);
    }

    @Override
    public void setOrderToCanceled(OrderCanceledRequest orderCanceledRequest) {
        // Order exists
        if(!iOrderServicePort.existsByIdClient(orderCanceledRequest.getIdClient())){
            throw new OrderDoNotExistsException();
        }

        Order order = iOrderServicePort.getOrderByIdClient(orderCanceledRequest.getIdClient());
        //Only pending orders are allowed to be canceled
        if(!order.getState()
                .equals("Pendiente"))
        {
            String body = "We're sorry, your order was already taken by the restaurant and can't be canceled";
            twilioConfiguration.sendSMS(body);
            throw new OrderIsNotPendingException();
        }

        order.setState("Cancelado");
        iOrderServicePort.saveOrder(order);

        String body = "Tu orden fue cancelada";
        twilioConfiguration.sendSMS(body);
    }
    }

