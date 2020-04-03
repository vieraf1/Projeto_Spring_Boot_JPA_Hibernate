package com.thannos.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thannos.projeto.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
