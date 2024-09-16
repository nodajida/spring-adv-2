package com.sparta.adv2.domain.auth.service;

import com.sparta.adv2.confing.JwtUtil;
import com.sparta.adv2.confing.PasswordEncoder;
import com.sparta.adv2.domain.auth.dto.SignupRequest;
import com.sparta.adv2.domain.auth.dto.SignupResponse;
import com.sparta.adv2.domain.auth.dto.SigninRequest;
import com.sparta.adv2.domain.common.exception.InvalidRequestException;
import com.sparta.adv2.domain.user.entity.User;
import com.sparta.adv2.domain.user.enums.UserRole;
import com.sparta.adv2.domain.user.repository.UserRepository;
import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public SignupResponse signup(@Valid SignupRequest signinRequest) {
        //비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());
        //사용자 역할 확인
        UserRole userRole = UserRole.of(signupRequest.getUserRole());
        //이메일 중복 체크
        if (userRepository.existsByEmail(signinRequest.getEmail())) {
            throw new InvalidRequestException("이미 존재하는 이메일입니다.");
        }

        //새로운 사영자 만들기
        User newUser = new User(
                signinRequest.getEmail(),
                encodedPassword,
                userRole
        );

        //회원 저장하기
        User saveUser = userRepository.save(newUser);
        //토큰 만들기
        String bearerToken = jwtUtil.createToken(saveUser.getId(), saveUser.getEmail, userRole);
        //토큰을 만들어 회원 가입이 성공적으로 완료되었다는 응답 보내기.
        return new SignupResponse(bearerToken);
    }

    //로그인 처리 signin
    public SignupResponse signin(SigninRequest signinRequest) {
        //사용자 찾기
        User user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(
                () -> new InvalidRequestException("가입되지 않은 유저입니다."));
        //비밀번호 확인
        if(!passwordEncoder.matches(signinRequest.getPassword(), user.getPassword())){
            throw new AuthException("잘못된 비밀번호입니다.");
        }
        //토큰 만들기
        String bearerToken = jwtUtil.createToken(user.getId(), user.getEmail(), user.getPassword());
        //로그인 성공하면 토큰으로 응답 보내기
        return new SignupResponse(bearerToken);
    }


}
