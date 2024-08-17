package com.prueba.personaltraining.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.prueba.personaltraining.model.MeasureHistoryEntity}
 */
public record MeasureHistoryDto(Integer id, Integer reps, Float weight) implements Serializable {
}