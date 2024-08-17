package com.prueba.personaltraining.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.prueba.personaltraining.model.RoleEntity}
 */
public record RoleDto(Integer id, String role, String description) implements Serializable {
}