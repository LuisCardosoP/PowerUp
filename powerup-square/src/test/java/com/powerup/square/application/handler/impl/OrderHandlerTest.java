package com.powerup.square.application.handler.impl;


import com.powerup.square.application.dto.OrderRequest;
import com.powerup.square.application.dto.OrderState;
import com.powerup.square.application.dto.OrderUpdateStateRequest;
import com.powerup.square.application.mapper.IOrderRequestMapper;
import com.powerup.square.application.mapper.IPlateResponseMapper;
import com.powerup.square.domain.api.IEmployeeServicePort;
import com.powerup.square.domain.api.IOrderPlatesServicePort;
import com.powerup.square.domain.api.IOrderServicePort;
import com.powerup.square.domain.api.IPlateServicePort;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.model.Plate;
import com.powerup.square.domain.model.Restaurant;
import com.powerup.square.domain.spi.IOrderPersistencePort;
import com.powerup.square.domain.spi.IOrderPlatesPersistencePort;
import com.powerup.square.domain.spi.IPlatePersistencePort;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Date;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class OrderHandlerTest {

    @InjectMocks
    OrderHandler orderHandler;

    @Mock
    IEmployeeServicePort iEmployeeServicePort;
    @Mock
    IOrderPlatesServicePort orderPlatesServicePort;
    @Mock
    IOrderPlatesPersistencePort orderPlatesPersistencePort;

    @Mock
    IOrderPersistencePort orderPersistencePort;
    @Mock
    IRestaurantPersistencePort iRestaurantPersistencePort;
    @Mock
    IPlateServicePort plateServicePort;
    @Mock
    Plate plate;
    @Mock
    IEmployeeServicePort employeeServicePort;
    @Mock
    IPlateResponseMapper plateResponseMapper;

    @Mock
    IOrderRequestMapper iOrderRequestMapper;

    @Mock
    IOrderServicePort iOrderServicePort;

    @Mock
    IPlatePersistencePort iPlatePersistencePort;

/*
    @Test
    void saveOrder(){

        Order order = SaveOrderHandlerDataTest.obtainOrder();
        OrderRequest orderRequest = SaveOrderHandlerDataTest.obtainOrderRequest();

        orderHandler.saveOrder(orderRequest);
    }*/

    @Test
    void saveOrder(){
        //Given
        Order order = SaveOrderHandlerDataTest.obtainOrder();
        OrderRequest orderRequest = SaveOrderHandlerDataTest.obtainOrderRequest();

        when(iOrderRequestMapper.toOrder(orderRequest)).thenReturn(order);
        when(iOrderServicePort.getOrderByIdClient(anyLong())).thenReturn(order);
        when(iPlatePersistencePort.getPlate(anyLong())).thenReturn(SavePlateHandlerDataTest.obtainPlate());
        System.out.println(orderRequest);

        orderHandler.saveOrder(orderRequest);

        verify(iOrderServicePort).saveOrder(order);
    }



    /*
    @Test
    void GetAllOrdersByState(){
        OrderState ordersStateRequest = SaveOrderHandlerDataTest.obtainOrdersStaterequest();
        int page = 0;
        int size = 2;

    //   when(iEmployeeServicePort.getEmployee(anyLong())).thenReturn(SaveEmployeeHandlerDataTest.obtainEmployee());

        orderHandler.getAllOrdersByState(page, size, ordersStateRequest);
    }*/


    /*
    @Test
    void updateOrderToAsignIt(){
        Order order = SaveOrderHandlerDataTest.obtainOrder();
        order.setState("Preparando");

        OrderUpdateStateRequest orderUpdateStateRequest = SaveOrderHandlerDataTest.obtainOrderUpdateStateRequest();

        when(iOrderServicePort.getOrderById(anyLong())).thenReturn(order);

        orderHandler.updateOrderAssign(orderUpdateStateRequest);
    }*/




}
