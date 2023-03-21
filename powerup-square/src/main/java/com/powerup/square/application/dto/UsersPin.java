package com.powerup.square.application.dto;

import java.util.HashMap;

public class UsersPin {

    private Long idClient;
    private Long pin;

    public static HashMap<Long, HashMap<String, Object>> clientList;

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Long getPin() {
        return pin;
    }

    public void setPin(Long pin) {
        this.pin = pin;
    }

}
