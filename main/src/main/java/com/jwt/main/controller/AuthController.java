package com.jwt.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.main.dto.AuthRequest;
import com.jwt.main.dto.AuthResponse;
import com.jwt.main.dto.JsonResult;
import com.jwt.main.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public JsonResult<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest) {
        return authService.login(authRequest);
    }
}