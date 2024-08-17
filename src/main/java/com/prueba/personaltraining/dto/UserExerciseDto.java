package com.prueba.personaltraining.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.prueba.personaltraining.model.UserExerciseEntity}
 */
public record UserExerciseDto(String maxReps, String maxWeight) implements Serializable {
}