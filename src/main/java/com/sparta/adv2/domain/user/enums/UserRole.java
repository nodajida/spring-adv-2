package com.sparta.adv2.domain.user.enums;

import com.sparta.adv2.domain.common.exception.InvalidRequestException;

import java.util.Arrays;

public enum UserRole {
    ADMIN, USER;

    public static UserRole of(String role) {
        return Arrays.stream(UserRole.values())
                .filter(r -> r.name().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new InvalidRequestException("유효하지 않은 UerRole"));
    }
}

