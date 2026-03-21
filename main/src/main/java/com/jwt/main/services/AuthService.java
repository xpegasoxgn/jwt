package com.jwt.main.services;

import com.jwt.main.dto.AuthRequest;
import com.jwt.main.dto.AuthResponse;
import com.jwt.main.dto.JsonResult;
public interface AuthService {

    public JsonResult<AuthResponse> login(AuthRequest authRequest);
}
