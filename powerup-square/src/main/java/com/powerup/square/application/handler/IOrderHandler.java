package com.powerup.square.application.handler;

import com.powerup.square.application.dto.OrderUpdateStateRequest;
import com.powerup.square.application.dto.OrderRequest;
import com.powerup.square.application.dto.OrderResponse;
import com.powerup.square.application.dto.OrderState;
import com.powerup.square.domain.model.OrderPlates;

import java.util.List;

public interface IOrderHandler {

     void updateOrderAssign(OrderUpdateStateRequest orderUpdateStateRequest);

    List<OrderResponse> getAllOrdersByState(int page, int size,OrderState orderState);
    void saveOrder(OrderRequest orderRequest);


    List<OrderPlates> getOrderPlatesById(Long id);
}
