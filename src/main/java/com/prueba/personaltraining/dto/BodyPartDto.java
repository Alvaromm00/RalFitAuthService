package com.prueba.personaltraining.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.prueba.personaltraining.model.BodyPartEntity}
 */
public record BodyPartDto(Integer id, String name) implements Serializable {
}