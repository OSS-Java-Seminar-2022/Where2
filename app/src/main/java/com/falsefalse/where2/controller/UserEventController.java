package com.falsefalse.where2.controller;

import com.falsefalse.where2.persistence.repositories.UserRepository;
import com.falsefalse.where2.security.SecurityUtil;
import com.falsefalse.where2.service.UserEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("event")
public class UserEventController {
    private final UserEventService userEventService;
    private final UserRepository userRepository;

    @GetMapping("/subscribe/{eventId}")
    public String subscribe(@PathVariable Integer eventId) {
        var username = SecurityUtil.getSessionUser();
        if (username == null) return "redirect:/login";

        var userId = userRepository.findByUsername(username).orElseThrow().getId();
        userEventService.subscribe(eventId, userId);
        return "redirect:/events";
    }

    @GetMapping("/unsubscribe/{eventId}")
    public String unsubscribe(@PathVariable Integer eventId) {
        var username = SecurityUtil.getSessionUser();
        if (username == null) return "redirect:/login";
        var userId = userRepository.findByUsername(username).orElseThrow().getId();
        userEventService.unsubscribe(eventId, userId);
        return "redirect:/events";
    }
}
