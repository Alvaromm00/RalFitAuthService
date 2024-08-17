package com.prueba.personaltraining.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private boolean success;
    private String message;
    private String token;
}
