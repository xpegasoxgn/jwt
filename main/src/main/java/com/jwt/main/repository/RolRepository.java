package com.jwt.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.main.entity.Rol;


public interface RolRepository extends JpaRepository<Rol, Long> {
      Optional<Rol> findByCodigo(String codigo);

    boolean existsByCodigo(String codigo);
    List<Rol> findByCodigoInAndActivoTrue(List<String> codigos);
}
