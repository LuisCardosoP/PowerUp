package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.OrderRequest;
import com.powerup.square.application.handler.IOrderHandler;
import com.powerup.square.application.mapper.IOrderRequestMapper;
import com.powerup.square.domain.api.IOrderPlatesServicePort;
import com.powerup.square.domain.api.IOrderServicePort;
import com.powerup.square.domain.spi.IPlatePersistencePort;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class OrderHandler implements IOrderHandler {


    private final IOrderServicePort iOrderServicePort;

    private final IOrderRequestMapper iOrderRequestMapper;

    private final IOrderPlatesServicePort iOrderPlatesServicePort;

    private final IRestaurantPersistencePort iRestaurantPersistencePort;

    private final IPlatePersistencePort iPlatePersistencePort;

    public OrderHandler(IOrderServicePort iOrderServicePort, IOrderRequestMapper iOrderRequestMapper, IOrderPlatesServicePort iOrderPlatesServicePort, IRestaurantPersistencePort iRestaurantPersistencePort, IPlatePersistencePort iPlatePersistencePort) {
        this.iOrderServicePort = iOrderServicePort;
        this.iOrderRequestMapper = iOrderRequestMapper;
        this.iOrderPlatesServicePort = iOrderPlatesServicePort;
        this.iRestaurantPersistencePort = iRestaurantPersistencePort;
        this.iPlatePersistencePort = iPlatePersistencePort;
    }

    @Override
    public void saveOrder(OrderRequest orderRequest) {

    }
}
