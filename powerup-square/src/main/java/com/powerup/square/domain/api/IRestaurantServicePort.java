package com.powerup.square.domain.api;

import com.powerup.square.application.dto.RestaurantListRequest;
import com.powerup.square.domain.model.Restaurant;
import java.util.List;


public interface IRestaurantServicePort {
    void saveRestaurant(Restaurant restaurant);

    Restaurant getRestaurant(Long id);

    List<Restaurant> getAllRestaurant(RestaurantListRequest restaurantListRequest);
}
