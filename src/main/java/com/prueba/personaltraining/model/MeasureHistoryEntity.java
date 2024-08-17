package com.prueba.personaltraining.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`measure-history`")
public class MeasureHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`measurement-id`", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "`user-id`", nullable = false)
    private UserEntity userId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "`exercise-id`", nullable = false)
    private ExerciseEntity exerciseId;

    @Column(name = "reps")
    private Integer reps;

    @Column(name = "weight")
    private Float weight;

}