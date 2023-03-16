package com.powerup.square.application.mapper;


import com.powerup.square.application.dto.OrderResponse;
import com.powerup.square.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderResponseMapper {

    OrderResponse toOrderResponse(Order order);
    List<OrderResponse> toOrderResponseList(List<Order> order);
}
