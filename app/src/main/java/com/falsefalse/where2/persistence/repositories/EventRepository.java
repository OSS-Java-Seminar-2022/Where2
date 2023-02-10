package com.falsefalse.where2.persistence.repositories;

import com.falsefalse.where2.persistence.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Integer> {
    List<EventEntity> findAllByVenueName(String venueName);

}
