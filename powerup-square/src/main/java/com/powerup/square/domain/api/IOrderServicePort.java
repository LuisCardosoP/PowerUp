package com.powerup.square.domain.api;

import com.powerup.square.domain.model.Order;

import java.util.List;

public interface IOrderServicePort {

    void saveOrder(Order order);


   // List<Order> getAllOrder();

   // List<Order> getAllOrdersByState(OrderStateRequest state);

    Order getOrderByIdClient(Long idClient);
    boolean existsByIdClient(Long idClient);

}
