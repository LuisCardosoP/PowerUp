package com.powerup.square.domain.api;

import com.powerup.square.application.dto.OrderRequest;
import com.powerup.square.application.dto.OrderState;
import com.powerup.square.domain.model.Order;

import java.util.List;

public interface IOrderServicePort {

    void saveOrder(Order order);


   // List<Order> getAllOrder();

    List<Order> getAllOrdersByState(int page, int size, OrderState state);

    Order getOrderByIdClient(Long idClient);
    boolean existsByIdClient(Long idClient);

    void updateOrderAssign(List<Order> order);

    Order getOrderById(Long idOrder);

}
