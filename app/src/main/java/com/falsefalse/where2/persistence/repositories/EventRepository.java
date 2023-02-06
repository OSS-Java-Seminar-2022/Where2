package com.falsefalse.where2.persistence.repositories;

import com.falsefalse.where2.persistence.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Integer> {
}
