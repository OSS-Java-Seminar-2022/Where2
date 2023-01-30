package com.falsefalse.where2.models;

import lombok.Builder;

@Builder
public record UserModel(Integer id, String displayName) {
}
