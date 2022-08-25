package com.sparta.blogs.controller;

import com.sparta.blogs.dto.CommentRequestDto;
import com.sparta.blogs.model.Comment;
import com.sparta.blogs.security.UserDetailsImpl;
import com.sparta.blogs.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    // 댓글등록
    @PostMapping("comments/{postId}")
    public Comment create(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Comment comment = commentService.createComment(postId, userDetails, requestDto);
        return comment;
    }
    //댓글수정
    @PutMapping("comments/{id}")
    public Long update(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.updateComment(id, userDetails, requestDto);
        return id;
    }

    //댓글삭제
    @DeleteMapping("comments/{id}")
    public Long delete(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(id, userDetails);
        return id;
    }




}
