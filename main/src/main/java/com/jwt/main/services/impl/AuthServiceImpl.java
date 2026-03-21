package com.jwt.main.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.main.dto.AuthRequest;
import com.jwt.main.dto.AuthResponse;
import com.jwt.main.dto.JsonResult;
import com.jwt.main.entity.Usuario;
import com.jwt.main.repository.UsuarioRespository;
import com.jwt.main.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsuarioRespository usuarioRespository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public JsonResult<AuthResponse> login(AuthRequest authRequest) {
        Optional<Usuario> usuarioOptional =
                usuarioRespository.findByUsernameAndActivoTrue(authRequest.getUsername());

        if (usuarioOptional.isEmpty()) {
            return new JsonResult<>(false, "Usuario no encontrado", null);
        }

        Usuario usuario = usuarioOptional.get();

        boolean passwordMatch = passwordEncoder.matches(
                authRequest.getPassword(),
                usuario.getPassword()
        );

        if (!passwordMatch) {
            return new JsonResult<>(false, "Contraseña incorrecta", null);
        }

        String token = "tokenficticio";

        AuthResponse authResponse = new AuthResponse(
                token,
                usuario.getUsername(),
                usuario.getNombre() + " " + usuario.getApellido(),
                null
        );

        return new JsonResult<>(true, "Login exitoso", authResponse);
    }
}