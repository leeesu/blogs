package com.sparta.blogs.controller;

import com.sparta.blogs.dto.UserInfoDto;
import com.sparta.blogs.dto.UserRequestDto;
import com.sparta.blogs.model.User;
import com.sparta.blogs.repository.UserRepository;
import com.sparta.blogs.security.UserDetailsImpl;
import com.sparta.blogs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;


    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public User registerUser(UserRequestDto requestDto) {
        User user = userService.registerUser(requestDto);

        return user;
    }

    // 회원 관련 정보 받기
    @PostMapping("/user/userinfo")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();

        return new UserInfoDto(username);
    }

    @GetMapping("/user/login")
    public User login(UserRequestDto userRequestDto, HttpServletRequest request) {

        User user = userRepository.findByUsername(userRequestDto.getUsername()).orElseThrow(
                () -> new RuntimeException("사용자 없습니다.")
        );
        return user;
    }
}
