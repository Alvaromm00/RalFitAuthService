package com.prueba.personaltraining.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`body-part`")
public class BodyPartEntity {
    @Id
    @Column(name = "body_part_id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

}