package com.powerup.square.domain.usecase;

import com.powerup.square.application.dto.RestaurantListRequest;
import com.powerup.square.domain.api.IRestaurantServicePort;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import com.powerup.square.domain.model.Restaurant;

import java.util.List;
public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }
    @Override
    public void saveRestaurant(Restaurant restaurant) {
        restaurantPersistencePort.saveRestaurant(restaurant);
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        return restaurantPersistencePort.getRestaurant(id);
    }

    @Override
    public List<Restaurant> getAllRestaurant(RestaurantListRequest restaurantListRequest) {
        return restaurantPersistencePort.getAllRestaurant(restaurantListRequest);
    }
}
