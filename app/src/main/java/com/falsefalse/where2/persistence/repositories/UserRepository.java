package com.falsefalse.where2.persistence.repositories;

import com.falsefalse.where2.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String userName);
    Optional<UserEntity> findFirstByUsername(String userName);
    Boolean existsByUsername(String userName);
    Boolean existsByEmail(String email);
}
