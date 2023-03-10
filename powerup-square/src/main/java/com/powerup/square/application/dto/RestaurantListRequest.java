package com.powerup.square.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class RestaurantListRequest {


    private Long amount;

    private Long page;
    @NotBlank(message = "el campo es sort es obligatorio")
    private String sort;
}
