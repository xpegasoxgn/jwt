package com.jwt.main.services;

import com.jwt.main.dto.AuthRequest;
import com.jwt.main.dto.AuthResponse;
import com.jwt.main.dto.JsonResult;
import com.jwt.main.dto.RegisterRequest;
import com.jwt.main.dto.RegisterResponse;
public interface AuthService {

    public JsonResult<AuthResponse> login(AuthRequest authRequest);
    public JsonResult<RegisterResponse> register(RegisterRequest registerRequest);
}
