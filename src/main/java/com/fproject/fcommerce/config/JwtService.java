package com.fproject.fcommerce.config;

import org.springframework.stereotype.Service;

import com.fproject.fcommerce.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private static final String SECRET_KEY = "mySuperSecretKeyForJwtAuthentication123456789";

    public String generateToken(User user) {

        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .claim("name", user.getName())
                .claim("enabled", user.isEnabled())
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key)
                .compact();
    }

    public String extractEmail(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Long extractUserId(String token) {

        Object id = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("userId");

        return Long.valueOf(id.toString());
    }

    public boolean isTokenValid(String token) {

        try {

            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                    .build()
                    .parseClaimsJws(token);

            return true;

        } catch (Exception e) {

            return false;

        }
    }
}
