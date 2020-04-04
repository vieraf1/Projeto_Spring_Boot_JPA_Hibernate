package com.thannos.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thannos.projeto.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
