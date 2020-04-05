package com.thannos.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thannos.projeto.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
