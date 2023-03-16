package com.powerup.square.application.handler;

import com.powerup.square.application.dto.OrderRequest;
import com.powerup.square.application.dto.OrderResponse;

import java.util.List;

public interface IOrderHandler {


 //   List<OrderResponse> getAllOrdersByState(OrderStateRequest ordersStateRequest);
    void saveOrder(OrderRequest orderRequest);
}
