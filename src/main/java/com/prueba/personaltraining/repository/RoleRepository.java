package com.prueba.personaltraining.repository;

import com.prueba.personaltraining.model.RoleEntity;
import com.prueba.personaltraining.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
}