package com.prueba.personaltraining.dto;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.prueba.personaltraining.model.UserEntity}
 */
public record UserDto(Integer id, String name, String lastName, String email, String password, Instant createTime,
                      String dni, String status) implements Serializable {
}