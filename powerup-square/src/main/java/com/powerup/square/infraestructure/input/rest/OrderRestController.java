package com.powerup.square.infraestructure.input.rest;


import com.powerup.square.application.dto.OrderRequest;
import com.powerup.square.application.dto.OrderResponse;
import com.powerup.square.application.dto.OrderState;
import com.powerup.square.application.dto.OrderUpdateStateRequest;
import com.powerup.square.application.handler.impl.OrderHandler;
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



    @PostMapping("/ordersByState")
    public ResponseEntity<List<OrderResponse>> getOrdersByState(
            @Validated @RequestBody OrderState ordersStateRequest,
            @RequestParam int page,
            @RequestParam int size
    ){
        return ResponseEntity.status(HttpStatus.OK).body(orderHandler.getAllOrdersByState(page, size, ordersStateRequest));
    }


    @Operation(summary = "Assign yourself some orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "", content = @Content),
            @ApiResponse(responseCode = "400", description = "", content = @Content)
    })
    @PostMapping("/asignOrder")
    public ResponseEntity<Void> assignOrderEntity(@RequestBody OrderUpdateStateRequest orderUpdateStateRequest){
        orderHandler.updateOrderAssign(orderUpdateStateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
