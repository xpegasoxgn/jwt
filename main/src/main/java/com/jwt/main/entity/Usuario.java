package com.jwt.main.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="username", nullable = false, unique = true, length=50)
    private String username;
    @Column(name="password", nullable = false, length=100)
    private String password;
    @Column(name="email", nullable = false, unique = true, length=100)
    private String email;
    @Column(name="nombre", nullable = false, length=100)    
    private String nombre;
    @Column(name="apellido", nullable = false, length=100)
    private String apellido;
    @Column(name="fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;
    @Column(name="activo", nullable = false)
    private boolean activo;

    @OneToMany(mappedBy = "usuario", cascade=CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
    private Set <UsuarioRol> roles = new HashSet<>();

    @PrePersist
    public void prePersist(){
        this.fechaCreacion = LocalDate.now();
        this.activo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Set<UsuarioRol> getRoles() {
        return roles;
    }

    public void setRoles(Set<UsuarioRol> roles) {
        this.roles = roles;
    }

    public Usuario(Long id, String username, String password, String email, String nombre, String apellido,
            LocalDate fechaCreacion, boolean activo, Set<UsuarioRol> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaCreacion = fechaCreacion;
        this.activo = activo;
        this.roles = roles;
    }
    
    

    
}
