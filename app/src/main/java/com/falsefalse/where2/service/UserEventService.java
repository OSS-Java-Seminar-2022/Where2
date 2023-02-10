package com.falsefalse.where2.service;

import com.falsefalse.where2.persistence.entities.UserEventEntity;
import com.falsefalse.where2.persistence.repositories.UserEventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserEventService {
    private final UserEventRepository userEventRepository;

    public HttpStatus subscribe(Integer eventId, Integer userId) {
        if (userEventRepository.existsByUserIdAndEventId(userId, eventId)) return HttpStatus.ALREADY_REPORTED;

        userEventRepository.save(UserEventEntity.builder().userId(userId).eventId(eventId).build());
        return HttpStatus.OK;
    }

    @Transactional
    public HttpStatus unsubscribe(Integer eventId, Integer userId) {
        if (userEventRepository.existsByUserIdAndEventId(userId, eventId))
            userEventRepository.deleteAllByEventIdAndUserId(eventId, userId);
        return HttpStatus.OK;
    }

    public List<UserEventEntity> getAllByUserId(Integer userId) {
        return userEventRepository.getAllByUserId(userId);
    }

    public List<UserEventEntity> getAllByEventId(Integer eventId) {
        return userEventRepository.getAllByEventId(eventId);
    }
}
