package com.powerup.square.domain.usecase;

import com.powerup.square.domain.model.Restaurant;

public class SaveRestaurantUseCaseDataTest {

    public static Restaurant obtainRestaurant() {
        Restaurant restaurant = new Restaurant(
                2L,
                " Hamburguer",
                "calle 53",
                23L,
                "301354423423479",
                "https://www.lagranja.com",
                "132456424"
        );

        return restaurant;
    }

}
