package com.sparta.blogs.service;

import com.sparta.blogs.dto.CommentRequestDto;
import com.sparta.blogs.model.Comment;
import com.sparta.blogs.model.Post;
import com.sparta.blogs.model.User;
import com.sparta.blogs.repository.CommentRepository;
import com.sparta.blogs.repository.PostRepository;
import com.sparta.blogs.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    //댓글작성
    public Comment createComment(@PathVariable Long postId, @RequestBody UserDetailsImpl userDetails, @PathVariable CommentRequestDto requestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        Comment comment = new Comment(requestDto, post, userDetails.getUser());
        commentRepository.save(comment);

        return comment;
    }

    //댓글 수정
    public Long updateComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        if (comment.getUser().getUsername().equals(userDetails.getUser().getUsername())) {
            comment.update(requestDto);
            return id;
        } else {
            System.out.println("아이디가 일치하지 않습니다.");
            return null;
        }
    }

        //댓글삭제
        public Long deleteComment(Long id, UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        if (comment.getUser().getUsername().equals(userDetails.getUser().getUsername())) {
            commentRepository.deleteById(id);
            return id;
        } else {
            System.out.println("아이디가 일치하지 않습니다.");
            return null;
        }
    }
}
