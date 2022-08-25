package com.sparta.blogs.dto;

import com.sparta.blogs.model.Post;
import com.sparta.blogs.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private String comment;
}
