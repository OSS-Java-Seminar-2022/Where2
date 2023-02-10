package com.falsefalse.where2.controller;

import com.falsefalse.where2.models.EventModel;
import com.falsefalse.where2.persistence.entities.UserEventEntity;
import com.falsefalse.where2.persistence.entities.enums.Currency;
import com.falsefalse.where2.persistence.entities.enums.EventType;
import com.falsefalse.where2.service.EventService;
import com.falsefalse.where2.service.UserEventService;
import com.falsefalse.where2.service.VenueService;
import com.falsefalse.where2.service.validation.Validator;
import com.falsefalse.where2.utils.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final UserEventService userEventService;
    private final VenueService venueService;
    private final CurrentUser currentUser;
    private final Validator validator;

    @GetMapping
    public String getAll(Model model) {
        var subs = userEventService.getAllByUserId(currentUser.get().getId());
        model.addAttribute("events", eventService.getAll());
        model.addAttribute("subs", subs.stream().map(UserEventEntity::getEventId).toList());
        return "allEvents";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Integer id, Model model) {
        var subs = userEventService.getAllByUserId(currentUser.get().getId());
        model.addAttribute("user", currentUser.get());
        model.addAttribute("event", eventService.get(id));
        model.addAttribute("isAdmin", currentUser.isAdmin());
        model.addAttribute("subs", subs.stream().map(UserEventEntity::getEventId).toList());
        return "event";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("types", EventType.values());
        model.addAttribute("event", new EventModel());
        model.addAttribute("currencies", Currency.values());
        model.addAttribute("venues", venueService.getAll());
        return "createEvent";
    }

    @PostMapping("/create/save")
    public String create(@ModelAttribute("event") EventModel event, BindingResult result, Model model) {
        validator.validate(event);
        eventService.create(event);
        return "redirect:/events";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        model.addAttribute("types", EventType.values());
        model.addAttribute("event", eventService.get(id));
        model.addAttribute("currencies", Currency.values());
        model.addAttribute("venues", venueService.getAll());
        return "editEvent";
    }

    @PutMapping("/edit/save/{id}")
    public String update(@PathVariable Integer id,
                         @ModelAttribute("event") EventModel event,
                         BindingResult result, Model model) {
        event.setId(id);
        validator.validate(event);
        eventService.update(id, event);
        return "redirect:/events";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Integer id) {
        eventService.delete(id);
        return "redirect:/events";
    }
}
