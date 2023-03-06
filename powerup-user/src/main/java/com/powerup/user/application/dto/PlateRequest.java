package com.powerup.user.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class PlateRequest {

    @NotBlank(message = "El campo nombre es obligatorio")
    private String name;

    @NotNull(message = "El campo idOwner es obligatorio")
    private Long idCategory;
    @NotBlank
    private String description;

    @Positive
    @Digits(integer=10, fraction=0)
    private Long price;

    @NotNull (message = "El campo idRestaurant es obligatorio")
    private Long idRestaurant;


    @NotBlank (message = "The url field is mandatory")
    private String urlImage;

}
