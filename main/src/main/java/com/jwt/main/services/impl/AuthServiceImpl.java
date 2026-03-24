package com.jwt.main.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.main.dto.AuthRequest;
import com.jwt.main.dto.AuthResponse;
import com.jwt.main.dto.JsonResult;
import com.jwt.main.dto.RegisterRequest;
import com.jwt.main.dto.RegisterResponse;
import com.jwt.main.entity.Rol;
import com.jwt.main.entity.Usuario;
import com.jwt.main.entity.UsuarioRol;
import com.jwt.main.repository.RolRepository;
import com.jwt.main.repository.UsuarioRespository;
import com.jwt.main.repository.UsuarioRolRepository;
import com.jwt.main.security.JwtService;
import com.jwt.main.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRolRepository usuarioRolRepository;

    @Autowired
    private UsuarioRespository usuarioRespository;

    @Autowired
    private RolRepository RolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

     @Autowired
    private JwtService jwtService;


    AuthServiceImpl(UsuarioRolRepository usuarioRolRepository) {
        this.usuarioRolRepository = usuarioRolRepository;
    }

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

        List<String> roles = usuarioRolRepository.findByUsuarioId(usuario.getId())
        .stream()
        .map(usuarioRol -> usuarioRol.getRol().getCodigo())
        .toList();

        AuthResponse authResponse = new AuthResponse(
                jwtService.generateToken(usuario, roles),
                usuario.getUsername(),
                usuario.getNombre() + " " + usuario.getApellido(),
                null
        );

        return new JsonResult<>(true, "Login exitoso", authResponse);
    }

    @Override
    public JsonResult<RegisterResponse> register(RegisterRequest registerRequest) {
        if(usuarioRespository.existsByUsername(registerRequest.getUsername())) {
            return new JsonResult<>(false, "El nombre de usuario ya está en uso", null);
        }
        if(usuarioRespository.existsByEmail(registerRequest.getEmail())) {
            return new JsonResult<>(false, "El correo electrónico ya está en uso", null);
        }
        List<Rol> roles = RolRepository.findByCodigoInAndActivoTrue(registerRequest.getRoles());
        if(roles.isEmpty())
        {
            return JsonResult.error( "Roles no válidos");
        }
        if (roles.size() != registerRequest.getRoles().size()) {
            return JsonResult.error("Algunos roles no son válidos");

        }
        Usuario usuario=new Usuario();
        usuario.setUsername(registerRequest.getUsername());
        usuario.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        usuario.setNombre(registerRequest.getNombre());
        usuario.setApellido("Vargas");
        usuario.setEmail(registerRequest.getEmail());
        usuario.setActivo(true);
        usuario.setFechaCreacion(LocalDate.now());
        usuarioRespository.save(usuario);
        List<Rol> rolesAsignados = new ArrayList<>();
        for(Rol rol:roles){
            UsuarioRol usuarioRol = new UsuarioRol();
            usuarioRol.setUsuario(usuario);
            usuarioRol.setRol(rol);
            usuarioRolRepository.save(usuarioRol);
            rolesAsignados.add(rol);
        }
        RegisterResponse registerResponse = new RegisterResponse(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getNombre() + " " + usuario.getApellido(),
                usuario.getEmail(),
                registerRequest.getRoles()
        );
        return JsonResult.success( "Registro exitoso", registerResponse);
    }
}