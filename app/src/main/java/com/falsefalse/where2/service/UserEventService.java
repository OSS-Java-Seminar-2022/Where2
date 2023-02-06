package com.falsefalse.where2.service;

import com.falsefalse.where2.persistence.repositories.EventRepository;
import com.falsefalse.where2.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public HttpStatus subscribe(Integer eventId, Integer userId){
        var event = eventRepository.findById(eventId).orElseThrow();
        var user = userRepository.findById(userId).orElseThrow();
        event.subscribe(user);
        eventRepository.save(event);
        return HttpStatus.OK;
    }
}
