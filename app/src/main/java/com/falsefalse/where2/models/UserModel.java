package com.falsefalse.where2.models;

import com.falsefalse.where2.persistence.entities.Role;
import lombok.Builder;

@Builder
public record UserModel(Integer id, String displayName, String username, String password, String email, Role role) {
}
