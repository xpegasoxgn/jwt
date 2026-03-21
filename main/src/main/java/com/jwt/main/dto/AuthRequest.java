package com.jwt.main.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthRequest {

    @NotBlank(message="El login no puede estar vacío")
    private String username;
    @NotBlank(message="la contraseña es obligatoria")
    private String password;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
