package com.sparta.blogs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class HelloController {

    @GetMapping("/helloword")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }
}
