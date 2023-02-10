package com.falsefalse.where2.controller;

import com.falsefalse.where2.models.VenueModel;
import com.falsefalse.where2.persistence.entities.UserVenueEntity;
import com.falsefalse.where2.persistence.entities.enums.VenueType;
import com.falsefalse.where2.service.EventService;
import com.falsefalse.where2.service.UserVenueService;
import com.falsefalse.where2.service.VenueService;
import com.falsefalse.where2.service.validation.Validator;
import com.falsefalse.where2.utils.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("venues")
@RequiredArgsConstructor
public class VenueController {
    private final VenueService venueService;
    private final CurrentUser currentUser;
    private final EventService eventService;
    private final UserVenueService userVenueService;

    private final Validator validator;

    @GetMapping
    public String getAll(Model model) {
        var subs = userVenueService.getAllByUserId(currentUser.get().getId());
        model.addAttribute("venues", venueService.getAll());
        model.addAttribute("subs", subs.stream().map(UserVenueEntity::getVenueId).toList());
        return "allVenues";
    }

    @GetMapping("{id}")
    public String get(@PathVariable Integer id, Model model) {
        var subs = userVenueService.getAllByUserId(currentUser.get().getId());
        var venue = venueService.get(id);
        model.addAttribute("user", currentUser.get());
        model.addAttribute("venue", venue);
        model.addAttribute("events", eventService.getAllByVenueName(venue.getName()));
        model.addAttribute("isAdmin", currentUser.isAdmin());
        model.addAttribute("subs", subs.stream().map(UserVenueEntity::getVenueId).toList());
        return "venue";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("types", VenueType.values());
        model.addAttribute("venue", new VenueModel());
        return "createVenue";
    }

    @PostMapping("/create/save")
    public String create(@ModelAttribute("venue") VenueModel venue, BindingResult result, Model model) {
        validator.validate(venue);
        venueService.create(venue);
        return "redirect:/venues";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        model.addAttribute("types", VenueType.values());
        model.addAttribute("venue", venueService.get(id));
        return "editVenue";
    }

    @PutMapping("/edit/save/{id}")
    public String update(@PathVariable Integer id,
                         @Validated @ModelAttribute("venue") VenueModel venue,
                         BindingResult result, Model model) {
        venue.setId(id);
        validator.validate(venue);
        venueService.update(id, venue);
        return "redirect:/venues";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Integer id) {
        venueService.delete(id);
        return "redirect:/venues";
    }
}
