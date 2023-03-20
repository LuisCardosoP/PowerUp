package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.OrderRequest;
import com.powerup.square.application.dto.OrderResponse;
import com.powerup.square.application.dto.OrderState;
import com.powerup.square.application.dto.OrderUpdateStateRequest;
import com.powerup.square.application.handler.IOrderHandler;
import com.powerup.square.application.mapper.IOrderRequestMapper;
import com.powerup.square.application.mapper.IOrderResponseMapper;
import com.powerup.square.domain.api.IOrderPlatesServicePort;
import com.powerup.square.domain.api.IOrderServicePort;
import com.powerup.square.domain.api.IPlateServicePort;
import com.powerup.square.domain.api.IRestaurantServicePort;
import com.powerup.square.domain.exception.PendingExistsException;

import com.powerup.square.domain.exception.PlateIsNotRestaurantException;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.model.OrderPlates;
import com.powerup.square.domain.spi.IPlatePersistencePort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional

public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort iOrderServicePort;

    private final IOrderRequestMapper iOrderRequestMapper;
    private final IOrderResponseMapper iOrderResponseMapper;

    private final IOrderPlatesServicePort iOrderPlatesServicePort;

    private final IRestaurantServicePort iRestaurantServicePort;

    private final IPlatePersistencePort iPlatePersistencePort;
    private final IPlateServicePort iPlateServicePort;

    public OrderHandler(IOrderServicePort iOrderServicePort, IOrderRequestMapper iOrderRequestMapper, IOrderResponseMapper iOrderResponseMapper, IOrderPlatesServicePort iOrderPlatesServicePort, IRestaurantServicePort iRestaurantServicePort, IPlatePersistencePort iPlatePersistencePort, IPlateServicePort iPlateServicePort) {
        this.iOrderServicePort = iOrderServicePort;
        this.iOrderRequestMapper = iOrderRequestMapper;
        this.iOrderResponseMapper = iOrderResponseMapper;
        this.iOrderPlatesServicePort = iOrderPlatesServicePort;
        this.iRestaurantServicePort = iRestaurantServicePort;
        this.iPlatePersistencePort = iPlatePersistencePort;
        this.iPlateServicePort = iPlateServicePort;
    }


    @Override
    public void updateOrderAssign(OrderUpdateStateRequest orderUpdateStateRequest) {
        List<Order> orderUpdated = new ArrayList<>();
        for(Long idOrder: orderUpdateStateRequest.getIdOrders()) {
            Order order = iOrderServicePort.getOrderById(idOrder);
            order.setState("Preparando");

           // order.setIdEmployee(orderUpdateStateRequest.getIdEmployee());

            orderUpdated.add(order);
        }

        iOrderServicePort.updateOrderAssign(orderUpdated);
    }

    @Override
    public List<OrderResponse> getAllOrdersByState(int page, int size, OrderState orderState) {
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
}
