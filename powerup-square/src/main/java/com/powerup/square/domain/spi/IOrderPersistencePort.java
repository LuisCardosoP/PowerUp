package com.powerup.square.domain.spi;

import com.powerup.square.domain.model.Order;

import java.util.List;

public interface IOrderPersistencePort {

    void saveOrder(Order order);

    // List<Order> getAllOrder();

    // List<Order> getAllOrdersByState(OrderStateRequest orderStateRequest);

    Order getOrderByIdClient(Long idClient);

    boolean existsByIdClient(Long idClient);
}
