package com.powerup.square.application.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

@Getter
@Setter
public class PlateRequest {

    @NotBlank (message = "El campo nombre es obligatorio")
    private String name;

    @NotNull (message = "El campo idCategory es obligatorio")
    private Long idCategory;
    @NotNull (message = "El campo descripcion es obligatorio")
    private String description;

    @Positive
    @Digits(integer=10, fraction=0)
    private Long price;

    @NotNull (message = "El campo idRestaurant es obligatorio")
    private Long idRestaurant;

    @NotBlank (message = "The url field is mandatory")
    private String urlImage;

}
