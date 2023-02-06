package com.falsefalse.where2.api;

import com.falsefalse.where2.models.EventModel;
import com.falsefalse.where2.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public List<EventModel> getAll() {
        return eventService.getAll();
    }

    @GetMapping("{id}")
    public EventModel get(@PathVariable Integer id) {
        return eventService.get(id);
    }

    @PostMapping
    public EventModel create(@RequestBody EventModel newEventModel) {
        return eventService.create(newEventModel);
    }

    @PutMapping("{id}")
    public EventModel update(@PathVariable Integer id, @RequestBody EventModel newEventModel) {
        return eventService.update(id, newEventModel);
    }

    @DeleteMapping("{id}")
    public HttpStatus delete(@PathVariable Integer id) {
        return eventService.delete(id);
    }
}
