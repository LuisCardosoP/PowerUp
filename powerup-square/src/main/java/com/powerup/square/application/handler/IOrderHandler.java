package com.powerup.square.application.handler;

import com.powerup.square.application.dto.OrderRequest;

public interface IOrderHandler {

    void saveOrder(OrderRequest orderRequest);
}
