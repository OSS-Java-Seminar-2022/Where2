package com.falsefalse.where2.service.validation;

import com.falsefalse.where2.models.EventModel;
import com.falsefalse.where2.models.VenueModel;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

@Service
public class Validator {
    public void validate(VenueModel venue) {
        Pattern pattern = Pattern.compile("\"[0-9a-zA-z_]+\"");
        Matcher matcher = pattern.matcher(venue.getName());
        if (venue.getName().isBlank() || isNull(venue.getName()) || !matcher.matches())
            throw new IllegalArgumentException("Invalid name.");

        if (isNull(venue.getVenueType()))
            throw new IllegalArgumentException("Venue type not selected.");

        if (venue.getWorkingHours().isBlank())
            throw new IllegalArgumentException("Working hours not set.");
    }

    public void validate(EventModel event) {
        Pattern pattern = Pattern.compile("\"[0-9a-zA-z_]+\"");
        Matcher matcher = pattern.matcher(event.getName());
        if (event.getName().isBlank() || isNull(event.getName()) || !matcher.matches())
            throw new IllegalArgumentException("Invalid name.");

        if (isNull(event.getEventType()))
            throw new IllegalArgumentException("Event type not selected.");

        if (isNull(event.getVenue()))
            throw new IllegalArgumentException("Venue not selected.");
    }
}
