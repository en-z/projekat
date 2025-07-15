package com.example.service.osoblje.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtService {
    @Value("${jwt-secret}")
    private String jwtSecret;

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public List<String> extractRoles(String token) {
        return extractAllClaims(token).get("roles", List.class);
    }

    public String extractUserId(String token) {
        Object id = extractAllClaims(token).get("id");
        return String.valueOf(id);
    }

    public boolean isTokenValid(String token) {
        try {
            return !extractAllClaims(token)
                    .getExpiration()
                    .before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
