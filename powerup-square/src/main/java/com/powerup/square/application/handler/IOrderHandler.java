package com.powerup.square.application.handler;

import com.powerup.square.application.dto.OrderRequest;
import com.powerup.square.application.dto.OrderResponse;
import com.powerup.square.application.dto.OrderState;

import java.util.List;

public interface IOrderHandler {


    List<OrderResponse> getAllOrdersByState(int page, int size,OrderState orderState);
    void saveOrder(OrderRequest orderRequest);
}
