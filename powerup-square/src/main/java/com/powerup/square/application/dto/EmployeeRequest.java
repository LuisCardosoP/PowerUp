package com.powerup.square.application.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EmployeeRequest {

    private Long idUser;
    private Long idRestaurant;
    @NotBlank(message = "el campo es field es obligatorio")
    private String field;
}
