package com.powerup.square.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class OrderResponse {

    private Long id;
    private Date date;
    private Long idClient;

    private String state;
}
