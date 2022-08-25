package com.sparta.blogs.dto;

import lombok.Getter;

@Getter //로그인을 위한 Dto
public class LoginRequestDto {
    private String username;
    private String password;
}
