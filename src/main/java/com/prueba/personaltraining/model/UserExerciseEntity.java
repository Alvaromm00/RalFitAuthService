package com.prueba.personaltraining.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`user-exercise`")
public class UserExerciseEntity {
    @EmbeddedId
    private UserExerciseEntityId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @MapsId("exerciseId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private ExerciseEntity exercise;

    @Column(name = "max_reps", length = 45)
    private String maxReps;

    @Column(name = "max_weight", length = 45)
    private String maxWeight;

}