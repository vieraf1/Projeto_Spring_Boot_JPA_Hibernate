package com.thannos.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thannos.projeto.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
