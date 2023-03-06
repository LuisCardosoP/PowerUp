package com.powerup.user.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PlateUpdatingRequest {

    @NotNull(message = "El campo id es obligatorio")
    private Long id;
    @NotBlank  (message = "El campo descripcion es obligatorio")
    private String description;

    @NotNull (message = "El campo price es obligatorio")
    private Long price;


    private Long idOwner;

}
