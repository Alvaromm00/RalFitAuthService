package com.prueba.personaltraining.service.api;

import com.prueba.personaltraining.model.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface JWTService {
    String createToken(UserEntity user);
    String getUsernameFromToken(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}
