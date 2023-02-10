package com.falsefalse.where2.models;

import com.falsefalse.where2.persistence.entities.enums.Currency;
import com.falsefalse.where2.persistence.entities.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventModel {
    private Integer id;

    private String name;

    private EventType eventType;

    private String imageUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startingTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endingTime;

    private float price;

    private Currency currency;

    private boolean isRecurring;
    private UserModel createdBy;
    private String description;
    private VenueModel venue;
    private Integer venueId;
    private List<UserModel> users;
}
