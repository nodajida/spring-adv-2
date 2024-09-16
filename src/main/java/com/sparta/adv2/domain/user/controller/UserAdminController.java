package com.sparta.adv2.domain.user.controller;

import com.sparta.adv2.domain.user.dto.UserRoleChangeRequest;
import com.sparta.adv2.domain.user.service.UserAdminService;
import com.sparta.adv2.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserAdminController {

    private final UserAdminService userAdminService;

    //특정 사용자의 역할을 변경하는 요청
    @PatchMapping("/admin/users/{userId}")
    public void changeUserRole(@PathVariable long userId, @RequestBody UserRoleChangeRequest userRoleChangeRequest) {
        userAdminService.changeUserRole(userId, userRoleChangeRequest);
    }
}
//이 메서드는 관리자가 특정 사용자의 역할을 변경할 수 있도록 해줍니다