package com.falsefalse.where2.utils;

import com.falsefalse.where2.persistence.entities.UserEntity;
import com.falsefalse.where2.persistence.entities.enums.Role;
import com.falsefalse.where2.security.SecurityUtil;
import com.falsefalse.where2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class CurrentUser {
    private final UserService userService;

    public UserEntity get() {
        var user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.getEntity(username);
        }
        return user;
    }

    public Boolean isAdmin() {
        var username = SecurityUtil.getSessionUser();
        if (isNull(username)) return false;
        return userService.getEntity(username).getRoles().contains(Role.ADMIN);
    }
}
