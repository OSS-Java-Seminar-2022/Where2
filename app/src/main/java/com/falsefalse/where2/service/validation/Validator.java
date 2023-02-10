package com.falsefalse.where2.service.validation;

import com.falsefalse.where2.models.EventModel;
import com.falsefalse.where2.models.VenueModel;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class Validator {
    public void validate(VenueModel venue) {
        if (venue.getName().isBlank() || isNull(venue.getName()))
            throw new IllegalArgumentException("Invalid venue name.");

        if (isNull(venue.getVenueType()))
            throw new IllegalArgumentException("Venue type not selected.");

        if (venue.getWorkingHours().isBlank())
            throw new IllegalArgumentException("Working hours not set.");
    }

    public void validate(EventModel event) {
        if (event.getName().isBlank() || isNull(event.getName()))
            throw new IllegalArgumentException("Invalid event name.");

        if (isNull(event.getEventType()))
            throw new IllegalArgumentException("Event type not selected.");

        if (isNull(event.getVenueId()))
            throw new IllegalArgumentException("Venue not selected.");
    }
}
