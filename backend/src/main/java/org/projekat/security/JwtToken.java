package org.projekat.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
@Component
public class JwtToken {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.date}")
    private long jwtExpirationDate;
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currDate = new Date();
        Date expireDate = new Date(currDate.getTime()+jwtExpirationDate);
        String token =Jwts.builder().subject(username).issuedAt(new Date()).expiration(expireDate).signWith(key()).compact();
        return token;
    }
    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
    public String getUsername(String token){
        return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token).getPayload().getSubject();
    }
    public boolean validateToken(String token){
        Jwts.parser().verifyWith((SecretKey) key()).build().parse(token);
        return true;
    }
}
