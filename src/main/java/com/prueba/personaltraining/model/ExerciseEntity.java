package com.prueba.personaltraining.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "exercise")
public class ExerciseEntity {
    @Id
    @Column(name = "exercise_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "`body-part-id`", nullable = false)
    private BodyPartEntity bodyPartId;

}