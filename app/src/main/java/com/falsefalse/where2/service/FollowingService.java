package com.falsefalse.where2.service;

import com.falsefalse.where2.models.Follow;
import com.falsefalse.where2.persistence.repositories.EventRepository;
import com.falsefalse.where2.persistence.repositories.UserEventRepository;
import com.falsefalse.where2.persistence.repositories.UserVenueRepository;
import com.falsefalse.where2.persistence.repositories.VenueRepository;
import com.falsefalse.where2.utils.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowingService {
    private final UserEventRepository userEventRepository;
    private final EventRepository eventRepository;
    private final UserVenueRepository userVenueRepository;
    private final VenueRepository venueRepository;
    private final CurrentUser currentUser;

    public List<Follow> getFollowing() {
        var follows = new ArrayList<Follow>();
        var userId = currentUser.get().getId();
        var eventIds = userEventRepository.getAllByUserId(userId).stream().map(
                e -> e.getEventId()
        ).toList();
        var venueIds = userVenueRepository.getAllByUserId(userId).stream().map(
                e -> e.getVenueId()
        ).toList();
        eventIds.forEach(eid -> {
                    var event = eventRepository.findById(eid).orElseThrow();
                    follows.add(new Follow(event.getName(), event.getImageUrl(), "event"));
                }
        );
        venueIds.forEach(vid -> {
                    var venue = venueRepository.findById(vid).orElseThrow();
                    follows.add(new Follow(venue.getName(), venue.getImageUrl(), "venue"));
                }
        );
        return follows;
    }
}
