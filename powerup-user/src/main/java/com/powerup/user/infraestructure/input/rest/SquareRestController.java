package com.powerup.user.infraestructure.input.rest;

import com.powerup.user.application.dto.*;
import com.powerup.user.infraestructure.RestaurateClientFeign.RestauranteClient.RestaurantClient;
import com.powerup.user.infraestructure.out.jpa.repository.IUserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/square")
@RequiredArgsConstructor
public class SquareRestController {
    private final RestaurantClient restaurantClient;

    private final IUserRepository userRepository;



    @Operation(summary = "Add restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User restaurant", content = @Content),
            @ApiResponse(responseCode = "409", description = "restaurant already exists", content = @Content)
    })
    @PostMapping("/restaurant")
    public ResponseEntity<RestaurantRequest> saveRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        RestaurantRequest restaurant = restaurantClient.saveRestaurante(restaurantRequest).getBody();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(restaurantRequest);
    }

    @Operation(summary = "Add plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "plate created", content = @Content),
            @ApiResponse(responseCode = "409", description = "plate already exists", content = @Content)
    })
    @PostMapping("/createPlate/")
    public ResponseEntity<PlateRequest> savePlateEntity( @RequestBody PlateRequest plateRequest){

        restaurantClient.savePlate(plateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "put plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "put created", content = @Content),
            @ApiResponse(responseCode = "409", description = "put already exists", content = @Content)
    })
        @PutMapping("/putPlate/")
        public ResponseEntity<Void> editPlate(@Validated @RequestBody PlateUpdatingRequest plateUpdatingRequest){
            plateUpdatingRequest.setIdOwner(userRepository.findByEmail(userLoginApplication()).get().getId());
            restaurantClient.editPlate(plateUpdatingRequest);
            return ResponseEntity.status(HttpStatus.OK).build();
        }

    @PutMapping("/putActivate/")
    public ResponseEntity<Void> editPlate(@RequestBody PlateUpdatingStateRequest plateUpdatingStateRequest){
        restaurantClient.activatePlate(plateUpdatingStateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Add employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "plate created", content = @Content),
            @ApiResponse(responseCode = "409", description = "plate already exists", content = @Content)
    })

    @PostMapping("/createEmployee")
    public ResponseEntity<Void> saveEmployeeEntity(@Validated @RequestBody EmployeeRequest employeeRequest){

        restaurantClient.saveEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Operation(summary = "Get restaurants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant gotten", content = @Content),
            @ApiResponse(responseCode = "400", description = "Restaurant do not exists", content = @Content)
    })
    @PostMapping("/allRestaurant")
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurant(@RequestBody RestaurantListRequest restaurantListRequest){
        return restaurantClient.getAllRestaurant(restaurantListRequest);
    }

    @PostMapping("/allPlates")
    public ResponseEntity<List<PlateResponse>> getPlatesRestaurant(@RequestBody PlateListRequest plateListRequest){
        return restaurantClient.getPlatesRestaurant(plateListRequest);
    }


    //


    public static String userLoginApplication() { // leer token
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }
        return userDetails.getUsername();
    }

}
