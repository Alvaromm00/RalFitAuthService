package com.prueba.personaltraining.service.impl;

import com.prueba.personaltraining.model.UserEntity;
import com.prueba.personaltraining.service.api.JWTService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {

    private static final long expirationTime = 1000 * 60 * 60 * 24;

    private static final String SECRET_KEY = "EJEMPLODEMIERDAEJEMPLODEMIERDAMIERDONEJEMPLODEMIERDAEJEMPLODEMIERDAMIERDONEJEMPLODEMIERDAEJEMPLODEMIERDAMIERDONEJEMPLODEMIERDAEJEMPLODEMIERDAMIERDONEJEMPLODEMIERDAEJEMPLODEMIERDAMIERDONEJEMPLODEMIERDAEJEMPLODEMIERDAMIERDON";


    @Override
    public String createToken(UserEntity user) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        byte[] keybytes = Keys.hmacShaKeyFor(SECRET_KEY.getBytes()).getEncoded();

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().getRole());


        return Jwts
                .builder()
                .setClaims(claims)
                .subject(user.getDni())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = null;
        try {
            username = getUsernameFromToken(token);
        }catch (Exception e) {
            System.out.println(e);
        }

        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private Claims getAllClaims(String token)
    {
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }
}
