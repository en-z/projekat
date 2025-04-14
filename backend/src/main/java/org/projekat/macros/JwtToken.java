package org.projekat.macros;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class JwtToken {
    private final String secret = "ucitajIzCFG"; //TODO(en):dodaj da se cita iz cfg ili env
    private final SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    private final long validTime = 3600000L;// 1h

    public String generateToken(String email){
        Date now = new Date();
        Date validity = new Date(now.getTime()+validTime);
        return Jwts.builder().subject(email).issuedAt(now).expiration(validity).signWith(key).compact();
    }
    public Claims extractClaims(String token){
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }
    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }
    public boolean isExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }
    public boolean validateToken(String token,String username){
        return (username.equals(extractUsername(token)) && !isExpired(token));
    }
}
