package com.powerup.user.infraestructure.RestaurateClientFeign.RestauranteClient;

import com.powerup.user.application.dto.PlateRequest;
import com.powerup.user.application.dto.PlateUpdatingRequest;
import com.powerup.user.application.dto.PlateUpdatingStateRequest;
import com.powerup.user.application.dto.RestaurantRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="plazoleta",url = "http://localhost:8282/" ) //configuration = CustomFeignConfig.class)
public interface RestaurantClient {
    //@PostMapping("/restaurante")
    @RequestMapping(method = RequestMethod.POST, value = "restaurants/createRestaurant/")
    public ResponseEntity<RestaurantRequest> saveRestaurante(@RequestBody RestaurantRequest restaurantRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/plates/createPlate/")
    public ResponseEntity<PlateRequest> savePlate(@RequestBody PlateRequest restaurantRequest);

    @RequestMapping(method = RequestMethod.PUT, value = "plates/putPlate/")
    public ResponseEntity<Void> editPlate(@RequestBody PlateUpdatingRequest plateUpdatingRequest);

    @RequestMapping(method = RequestMethod.PUT, value = "plates/putActivate/")
    public ResponseEntity<Void> activatePlate(@RequestBody PlateUpdatingStateRequest plateUpdatingStateRequest);

}
