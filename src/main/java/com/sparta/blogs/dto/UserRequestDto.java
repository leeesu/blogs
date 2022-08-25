package com.sparta.blogs.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserRequestDto {
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{4,12}$",
            message = "비밀번호는 최소 4자리이면서 12개 이하의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String username;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*#?&])[a-z\\d@$!%*#?&]{4,32}$",
            message = "비밀번호는 최소 4자리이면서 32개 이하의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String password;
}
