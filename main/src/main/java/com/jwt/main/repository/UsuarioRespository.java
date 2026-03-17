package com.jwt.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.main.entity.Usuario;

public interface  UsuarioRespository extends JpaRepository<Usuario, Long> {
    
}
