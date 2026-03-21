package com.jwt.main.dto;

import java.util.List;

public class AuthResponse {
    
    private String token;
    private String username;
    private String nombreCompleto;
    private List<String> roles;

    
    public AuthResponse(String token, String username, String nombreCompleto, List<String> roles) {
        this.token = token;
        this.username = username;
        this.nombreCompleto = nombreCompleto;
        this.roles = roles;
    }
    public List<String> getRoles() {
        return roles;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    
}
