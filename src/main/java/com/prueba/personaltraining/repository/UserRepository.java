package com.prueba.personaltraining.repository;

import com.prueba.personaltraining.model.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {


    UserEntity findByDni(String dni);

    @Query(value = "SELECT * FROM user u WHERE u.dni = ?1",nativeQuery = true)
    List<UserEntity> findByIdNative(String dni);

}
