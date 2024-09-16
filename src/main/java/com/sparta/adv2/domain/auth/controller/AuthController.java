package com.sparta.adv2.domain.auth.controller;

import com.sparta.adv2.domain.auth.dto.SignupRequest;
import com.sparta.adv2.domain.auth.dto.SignupResponse;
import com.sparta.adv2.domain.auth.dto.SigninRequest;
import com.sparta.adv2.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    //회원가입 signup
    @PostMapping("/auth/signup")
    public SignupResponse signup(@Valid @RequestBody SignupRequest signupRequest){
        return authService.signup(signupRequest);
    }

    //로그인 signin

    @PostMapping("/auth/signin")
    public SignupResponse signin(@Valid @RequestBody SigninRequest signinRequest){
        return authService.signin(signinRequest);
    }


}
