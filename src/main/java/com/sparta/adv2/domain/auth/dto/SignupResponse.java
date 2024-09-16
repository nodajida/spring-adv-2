package com.sparta.adv2.domain.auth.dto;

import lombok.Getter;

@Getter

public class SignupResponse {

    private final String bearerToken;

    public SignupResponse(String bearerToken) {
        this.bearerToken = bearerToken;
    }

}
