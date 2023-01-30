package com.falsefalse.where2.persistence.repositories;

import com.falsefalse.where2.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
