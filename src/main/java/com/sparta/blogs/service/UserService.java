package com.sparta.blogs.service;

import com.sparta.blogs.dto.UserRequestDto;
import com.sparta.blogs.model.User;
import com.sparta.blogs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;


    public User registerUser(UserRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        User user = new User(username, password);
        userRepository.save(user);
        return user;
    }


}
