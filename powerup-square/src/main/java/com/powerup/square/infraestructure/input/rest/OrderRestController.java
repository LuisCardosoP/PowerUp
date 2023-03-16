package com.powerup.square.infraestructure.input.rest;


import com.powerup.square.application.dto.OrderRequest;
import com.powerup.square.application.handler.impl.OrderHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderRestController {

     private final OrderHandler orderHandler;

    @Operation(summary = "Add order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plate created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Plate already exists", content = @Content)
    })
    @PostMapping("/createNewOrder")
    public ResponseEntity<Void> saveOrderEntity(@Validated @RequestBody OrderRequest orderRequest){
        orderHandler.saveOrder(orderRequest);


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
