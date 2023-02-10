package com.falsefalse.where2.controller;

import com.falsefalse.where2.persistence.repositories.UserRepository;
import com.falsefalse.where2.security.SecurityUtil;
import com.falsefalse.where2.service.UserVenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("venue")
public class UserVenueController {
    private final UserVenueService userVenueService;
    private final UserRepository userRepository;

    @GetMapping("subscribe/{venueId}")
    public String subscribe(@PathVariable Integer venueId) {
        var username = SecurityUtil.getSessionUser();
        if (username == null) return "/login";

        var userId = userRepository.findByUsername(username).orElseThrow().getId();
        userVenueService.subscribe(venueId, userId);
        return "redirect:/venues";
    }

    @GetMapping("unsubscribe/{venueId}")
    public String unsubscribe(@PathVariable Integer venueId) {
        var username = SecurityUtil.getSessionUser();
        if (username == null) return "/login";
        var userId = userRepository.findByUsername(username).orElseThrow().getId();
        userVenueService.unsubscribe(venueId, userId);
        return "redirect:/venues";
    }
}
