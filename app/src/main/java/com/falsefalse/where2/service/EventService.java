package com.falsefalse.where2.service;

import com.falsefalse.where2.models.EventModel;
import com.falsefalse.where2.persistence.repositories.EventRepository;
import com.falsefalse.where2.persistence.repositories.UserEventRepository;
import com.falsefalse.where2.persistence.repositories.VenueRepository;
import com.falsefalse.where2.service.mapper.EventMapper;
import com.falsefalse.where2.service.mapper.UserMapper;
import com.falsefalse.where2.utils.CurrentUser;
import jakarta.transaction.Transactional;
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
    private final UserEventRepository userEventRepository;
    private final VenueRepository venueRepository;
    private final CurrentUser currentUser;

    public List<EventModel> getAll() {
        return eventRepository.findAll().stream().map(EventMapper.INSTANCE::fromEntity).toList();
    }

    public List<EventModel> getAllByVenueName(String venueName) {
        return eventRepository.findAllByVenueName(venueName).stream().map(EventMapper.INSTANCE::fromEntity).toList();
    }

    public EventModel get(int id) {
        return EventMapper.INSTANCE.fromEntity(eventRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE, id))));
    }

    public EventModel create(EventModel newEventModel) {
        var user = currentUser.get();
        newEventModel.setCreatedBy(UserMapper.INSTANCE.fromEntity(user));
        var entity = EventMapper.INSTANCE.toEntity(newEventModel);
        entity.setVenue(venueRepository.findById(newEventModel.getVenueId()).orElseThrow(
                () -> new NoSuchElementException(String.format(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE, newEventModel.getVenueId()))));
        return EventMapper.INSTANCE.fromEntity(eventRepository.save(entity));
    }

    public EventModel update(Integer id, EventModel newEventModel) {
        var user = currentUser.get();
        newEventModel.setCreatedBy(UserMapper.INSTANCE.fromEntity(user));

        var oldEvent = eventRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE, id)));

        EventMapper.INSTANCE.updateEntity(newEventModel, oldEvent);
        oldEvent.setVenue(venueRepository.findById(newEventModel.getVenueId()).orElseThrow(
                () -> new NoSuchElementException(String.format(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE, id))));
        return EventMapper.INSTANCE.fromEntity(eventRepository.save(oldEvent));
    }

    @Transactional
    public HttpStatus delete(Integer id) {
        if (eventRepository.existsById(id)) {
            userEventRepository.deleteAllByEventId(id);
            eventRepository.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;

    }
}
