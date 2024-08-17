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
@Table(name = "role")
public class RoleEntity {
    @Id
    @Column(name = "role_id", nullable = false)
    private Integer id;

    @Column(name = "role", nullable = false, length = 45)
    private String role;

    @Column(name = "description", nullable = false)
    private String description;

}