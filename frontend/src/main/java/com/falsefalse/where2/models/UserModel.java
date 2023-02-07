package com.falsefalse.where2.models;

import com.falsefalse.where2.models.enums.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserModel {
    private Integer id;
    private String displayName;
    private String username;
    private String password;
    private String email;
    private Role role;
    private List<EventModel> subscribedTo;
    private String authToken;
}
