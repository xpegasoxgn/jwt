package com.jwt.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//maneja erroeres de accesso no autorizado por ejemplo cuando no mandan token o es invalido o esta expirado

@Component
public class JwtAuthenticationEntryPoint extends OncePerRequestFilter{
    
    @Autowired
    JwtService jwtService;
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {

        String path = request.getRequestURI(); 

        // por ejemplo si el path es /api/auth/login o /api/auth/register no se requiere token, entonces no se hace nada y se continua con el filtro
        if (path.equals("/auth/login") ) {
            filterChain.doFilter(request, response);
            return;
            
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": false, \"message\": \"Token no proporcionado\"}");
            return;
        }

        String token = authHeader.substring(7);
        try {
            Claims claims = jwtService.extractAlClaims(token);
            request.setAttribute("claims", claims);
            request.setAttribute("username", claims.getSubject());
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": false, \"message\": \"Token inválido o expirado\"}");
        }
    }
}
