package com.powerup.square.application.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderUpdateStateRequest {


    // listo por estado y asigno el id empleado al pedido
    private Long idEmployee;

    private List<Long> IdOrders;
}

