package com.jwt.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.main.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
    
}
