package com.powerup.square.application.handler.impl;


import com.powerup.square.application.dto.OrderRequest;
import com.powerup.square.application.mapper.IPlateResponseMapper;
import com.powerup.square.domain.api.IEmployeeServicePort;
import com.powerup.square.domain.api.IOrderPlatesServicePort;
import com.powerup.square.domain.api.IOrderServicePort;
import com.powerup.square.domain.api.IPlateServicePort;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.model.Plate;
import com.powerup.square.domain.spi.IOrderPersistencePort;
import com.powerup.square.domain.spi.IOrderPlatesPersistencePort;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class OrderHandlerTest {

    @InjectMocks
    OrderHandler orderHandler;
    @Mock
    IOrderPlatesServicePort orderPlatesServicePort;
    @Mock
    IOrderPlatesPersistencePort orderPlatesPersistencePort;
    @Mock
    IOrderServicePort orderServicePort;
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

/*
    @Test
    void saveOrder(){

        Order order = SaveOrderHandlerDataTest.obtainOrder();
        OrderRequest orderRequest = SaveOrderHandlerDataTest.obtainOrderRequest();

        orderHandler.saveOrder(orderRequest);
    }*/
}
