package com.prueba.personaltraining.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.prueba.personaltraining.model.ExerciseEntity}
 */
public record ExerciseDto(Integer id, String name) implements Serializable {
}