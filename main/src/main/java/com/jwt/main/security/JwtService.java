package com.jwt.main.security;

import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jwt.main.entity.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

//generar y validar los tokens jwt
@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.experation}")
    private long experation;

    private SecretKey getSigninKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Usuario usuario, List<String> roles){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + experation);

        return Jwts.builder()
                .subject(usuario.getUsername())
                .claim("nombreCompleto", usuario.getNombre())
                .claim("roles", roles)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigninKey())
                .compact();
                
        
    }
    public Claims extractAlClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
