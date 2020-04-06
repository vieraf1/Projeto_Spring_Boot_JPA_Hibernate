package com.thannos.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thannos.projeto.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
