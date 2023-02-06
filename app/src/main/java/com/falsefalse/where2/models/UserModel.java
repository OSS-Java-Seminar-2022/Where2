package com.falsefalse.where2.models;

import com.falsefalse.where2.persistence.entities.enums.Role;
import lombok.Builder;

import java.util.List;

@Builder
public record UserModel(
        Integer id,
        String displayName,
        String username,
        String password,
        String email,
        Role role,
        List<EventModel> subscribedTo
) {
}
