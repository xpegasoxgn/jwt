package com.jwt.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.main.entity.UsuarioRol;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long> {
    
     boolean existsByUsuarioIdAndRolId(Long usuarioId, Long rolId);

    List<UsuarioRol> findByUsuarioId(Long usuarioId);
}
