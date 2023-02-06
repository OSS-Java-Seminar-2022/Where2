package com.falsefalse.where2.api;

import com.falsefalse.where2.persistence.repositories.UserRepository;
import com.falsefalse.where2.service.UserEventService;
import com.falsefalse.where2.utils.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("subscribe")
@RequiredArgsConstructor
public class UserEventController {
    private final UserEventService userEventService;
    private final UserRepository userRepository;

    @PatchMapping("{event_id}")
    public HttpStatus subscribe(@PathVariable Integer event_id){
        var user_id = userRepository.findByUsername(CurrentUser.get().getUsername()).orElseThrow().getId();
        return userEventService.subscribe(event_id, user_id);
    }
}
