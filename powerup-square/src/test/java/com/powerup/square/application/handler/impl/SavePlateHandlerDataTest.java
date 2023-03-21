package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.PlateListRequest;
import com.powerup.square.application.dto.PlateRequest;
import com.powerup.square.application.dto.PlateUpdatingRequest;
import com.powerup.square.domain.model.Category;
import com.powerup.square.domain.model.Plate;
import com.powerup.square.domain.model.Restaurant;

public class SavePlateHandlerDataTest {

    public static Plate obtainPlate(){
        Plate plate = new Plate(
                2L,
                " Hamburguer",
                new Category(
                        1L,
                        "Hamburger",
                        "Has 2 breads"
                ),
                "carne y pollo",
                15L,
                new Restaurant(
                        100L,
                        "Luis ",
                        "Street 55",
                        2L,
                        "3013256565",
                        "www.logo.es",
                        "ASD-121832-YU"
                ),
                "www.burguer.com/asdas.png",
                true
        );

        return plate;
    }

    public static PlateRequest obtainPlateRequest(){
        PlateRequest plateRequest = new PlateRequest();

        plateRequest.setName(" Hamburguer");
        plateRequest.setIdCategory(1L);
        plateRequest.setDescription("Have nachos, sour cream, guacamole and pico de gallo");
        plateRequest.setPrice(15L);
        plateRequest.setIdRestaurant(10L);
        plateRequest.setUrlImage("www.burger.com/asdas.png");

        return plateRequest;
    }
    public static PlateListRequest obtainPlateListRequest(){
        PlateListRequest plateListRequest = new PlateListRequest();

        plateListRequest.setIdRestaurant(2L);
        plateListRequest.setPage(1L);
        plateListRequest.setAmount(1L);

        return plateListRequest;
    }
    public static PlateUpdatingRequest obtainPlateUpdatingRequest(){
        PlateUpdatingRequest plateUpdatingRequest = new PlateUpdatingRequest();

        plateUpdatingRequest.setId(100L);
        plateUpdatingRequest.setDescription("empanada");



        plateUpdatingRequest.setPrice(15L);
        plateUpdatingRequest.setIdOwner(2L);

        return plateUpdatingRequest;

    }

}
