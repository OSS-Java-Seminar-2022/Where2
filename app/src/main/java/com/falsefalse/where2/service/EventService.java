package com.falsefalse.where2.service;

import com.falsefalse.where2.models.EventModel;
import com.falsefalse.where2.persistence.repositories.EventRepository;
import com.falsefalse.where2.service.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EventService {

    private final static String NO_SUCH_ELEMENT_EXCEPTION_MESSAGE = "NoSuchElementException: Event with [id: %s] not found!";
    private final EventRepository eventRepository;

    public List<EventModel> getAll() {
        return eventRepository.findAll().stream().map(EventMapper.INSTANCE::fromEntity).toList();
    }

    public EventModel get(int id) {
        return EventMapper.INSTANCE.fromEntity(eventRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE, id))));
    }

    public EventModel create(EventModel newEventModel) {
        var created = eventRepository.save(EventMapper.INSTANCE.toEntity(newEventModel));
        return EventMapper.INSTANCE.fromEntity(created);
    }

    public EventModel update(Integer id, EventModel newEventModel) {
        var oldEvent = eventRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE, id)));
        EventMapper.INSTANCE.updateEntity(newEventModel, oldEvent);

        return EventMapper.INSTANCE.fromEntity(eventRepository.save(oldEvent));
    }

    public HttpStatus delete(Integer id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;

    }
}
