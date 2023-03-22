package com.powerup.square.application.handler;

import com.powerup.square.application.dto.*;
import com.powerup.square.domain.model.OrderPlates;

import java.util.List;

public interface IOrderHandler {

     void updateOrderAssign(OrderUpdateStateRequest orderUpdateStateRequest);

    List<OrderResponse> getAllOrdersByState(int page, int size,OrderState orderState);
    void saveOrder(OrderRequest orderRequest);


    void OrderOkNotify(OrderOkRequest orderOkRequest);;
    List<OrderPlates> getOrderPlatesById(Long id);

    void setOrderToCanceled(OrderCanceledRequest orderCanceledRequest);
}
