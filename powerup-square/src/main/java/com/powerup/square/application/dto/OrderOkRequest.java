package com.powerup.square.application.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderOkRequest {


    private Long idClient;
}
