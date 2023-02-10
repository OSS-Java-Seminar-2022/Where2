package com.falsefalse.where2.models;

import com.falsefalse.where2.persistence.entities.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserModel {
    private Integer id;
    private String displayName;
    private String username;
    private String password;
    private String email;
    private List<Role> roles;
}
