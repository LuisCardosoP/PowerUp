package com.powerup.square.domain.spi;

import com.powerup.square.application.dto.RestaurantListRequest;
import com.powerup.square.domain.model.Restaurant;

import java.util.List;


public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);
    List<Restaurant> getAllRestaurant();
    Restaurant getRestaurant(Long id);

    List<Restaurant> getAllRestaurant(RestaurantListRequest restaurantListRequest);
}
