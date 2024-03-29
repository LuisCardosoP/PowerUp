package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.RestaurantListRequest;
import com.powerup.square.application.dto.RestaurantRequest;
import com.powerup.square.domain.model.Restaurant;

public class SaveRestaurantHandlerDataTest {

    public static Restaurant obtainRestaurant(){

        Restaurant restaurant = new Restaurant(
                100L,
                " La asaderia",
                "Street 55",
                10L,
                "3013218534243240",
                "www.hola.com",
                "12315645459"
        );

        return restaurant;

    }

    public static RestaurantListRequest obtainRestaurantListRequest(){
        RestaurantListRequest restaurantListRequest = new RestaurantListRequest();

        restaurantListRequest.setAmount(3L);
        restaurantListRequest.setPage(0L);

        return restaurantListRequest;
    }

    public static RestaurantRequest obtainRestaurantRequest(){
        RestaurantRequest restaurantRequest = new RestaurantRequest();

        restaurantRequest.setName(" la hamburgueseria");
        restaurantRequest.setAddress("calle  25");
        restaurantRequest.setIdOwner(10L);


        restaurantRequest.setPhone("3013237026");
        restaurantRequest.setUrlLogo("www.holaa.es");
        restaurantRequest.setNit("31534434");

        return restaurantRequest;
    }

}
