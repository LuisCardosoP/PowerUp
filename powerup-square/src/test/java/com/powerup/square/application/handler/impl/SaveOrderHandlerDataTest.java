package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.OrderRequest;
import com.powerup.square.application.dto.OrderState;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.model.Restaurant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaveOrderHandlerDataTest {
/*
    public static Order obtainOrder() {
        return  new Order(1L,
                2L,
                new Date(),
                "PENDIENTE",
                null
                );
    }*/





    public static OrderRequest obtainOrderRequest () {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setIdClient(2L);
        orderRequest.setIdRestaurant(1L);
        List<Long> amountlist = new ArrayList<>();
        amountlist.add(1L);


        List<Long> plateslist = new ArrayList<>();
        plateslist.add(1L);
        orderRequest.setAmountPlates(amountlist);
        orderRequest.setIdPlates(plateslist);
        return  orderRequest;
    }

    public static OrderState obtainOrdersStaterequest(){
        OrderState ordersStateRequest = new OrderState();

        ordersStateRequest.setState("Pendiente");
        ordersStateRequest.setIdEmployee(44L);

        return ordersStateRequest;
    }

    public static Order obtainOrder(){
        return new Order(
                1L,
                44L,
                new Date(),
                "Pendiente",
                new Restaurant(
                        2L,
                        "La granja",
                        "calle 55",
                        2L,
                        "+573013237026",
                        "https:www.hola.com",
                        "315645451"
                   ),
                    null


        );

    }
}
