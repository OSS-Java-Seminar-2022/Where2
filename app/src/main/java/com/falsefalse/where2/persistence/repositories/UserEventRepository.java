package com.falsefalse.where2.persistence.repositories;

import com.falsefalse.where2.persistence.entities.UserEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEventRepository extends JpaRepository<UserEventEntity, Integer> {
    Boolean existsByUserIdAndEventId(Integer userId, Integer eventId);

    List<UserEventEntity> deleteAllByEventId(Integer eventId);

    List<UserEventEntity> deleteAllByEventIdAndUserId(Integer eventId, Integer userId);

    List<UserEventEntity> getAllByUserId(Integer userId);

    List<UserEventEntity> getAllByEventId(Integer eventId);
}

