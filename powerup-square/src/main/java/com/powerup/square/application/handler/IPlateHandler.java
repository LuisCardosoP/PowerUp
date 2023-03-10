package com.powerup.square.application.handler;

import com.powerup.square.application.dto.*;

import java.util.List;

public interface IPlateHandler {

    void savePlate(PlateRequest plateRequest);

    PlateResponse getPlate(Long id);
    void updatePlate(PlateUpdatingRequest plateUpdatingRequest);

    void activatePlate(PlateUpdatingStateRequest plateUpdatingStateRequest);

    List<PlateResponse> getPlatesRestaurant(PlateListRequest plateListRequest);
}
