package com.falsefalse.where2.dto;

import lombok.Data;

@Data
public class RegistrationDto {
    private Integer id;
    private String displayName;
    private String username;
    private String password;
    private String email;
}
