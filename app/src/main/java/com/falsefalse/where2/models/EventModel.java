package com.falsefalse.where2.models;

import com.falsefalse.where2.persistence.entities.enums.Currency;
import com.falsefalse.where2.persistence.entities.enums.EventType;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record EventModel (
        Integer id,
        String name,
        EventType eventType,
        Date startingTime,
        Date endingTime,
        List<UserModel> subscribedUsers,
        float price,
        Currency currency,
        boolean isRecurring
        ) {
}
