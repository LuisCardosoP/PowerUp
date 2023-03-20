package com.powerup.square.infraestructure.out.jpa.repository;

import com.powerup.square.infraestructure.out.jpa.entity.OrderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "SELECT * FROM public.orders WHERE state = :state AND id_restaurant = :idRestaurant", nativeQuery = true)
    List<OrderEntity> getOrdersByState(@Param("state") String state, @Param("idRestaurant")Long idRestaurant, Pageable pageable);
    OrderEntity getOrderByIdClient(Long idClient);

    boolean existsByIdClient(Long idClient);
    OrderEntity getOrderById(Long idOrder);


    // metodos para datos paginados
    List<OrderEntity> getOrdersByState(String state, Pageable pageable);
}
