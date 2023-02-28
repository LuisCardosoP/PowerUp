package com.powerup.user.infraestructure.input.rest;

import com.powerup.user.application.dto.UserRequest;
import com.powerup.user.application.dto.UserResponse;
import com.powerup.user.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserRestController {
    private final IUserHandler userHandler;
    @Operation(summary = "Add a new Owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "USer already exists", content = @Content)
    })
    @PostMapping("/Propietario/{idRole}")
    public ResponseEntity<Void> saveUserEntityOwner(@Validated @RequestBody UserRequest userRequest, @PathVariable Long idRole){
        if(idRole == 0) {
            userHandler.saveUser(userRequest, 1L);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Operation(summary = "Add a new Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "USer already exists", content = @Content)
    })
    @PostMapping("/Employee/{idRole}")
    public ResponseEntity<Void> saveUserEntityEmployee(@Validated @RequestBody UserRequest userRequest, @PathVariable Long idRole){
        if(idRole == 1){
                userHandler.saveUser(userRequest, 2L);
                return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @Operation(summary = "Add a new Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "USer already exists", content = @Content)
    })
    @PostMapping("/Client")
    public ResponseEntity<Void> saveUserEntityClient(@Validated @RequestBody UserRequest userRequest){
        userHandler.saveUser(userRequest, 3L);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "USer already exists", content = @Content)
    })
    @GetMapping("/GET/UserById/{id}")
    public UserResponse getUserById(@PathVariable Long id){
        System.out.println(id);
        UserResponse userResponse = userHandler.getUser(id);
        return userResponse;
    }



}