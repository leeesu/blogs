package com.sparta.blogs.controller;

import com.sparta.blogs.dto.PostRequestDto;
import com.sparta.blogs.model.Post;
import com.sparta.blogs.model.User;
import com.sparta.blogs.repository.PostRepository;
import com.sparta.blogs.security.UserDetailsImpl;
import com.sparta.blogs.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
    private final PostRepository blogRepository;

    @PostMapping("/posts")
    public Post createBlog(@RequestBody PostRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Post post = postService.createPost(requestDto, userDetails);
        return post;
    }

    @GetMapping("/posts")
    public List<Post> getBlog() {

        return postService.listPost();
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> detailBlog(@PathVariable Long id) {

        return postService.detailPost(id);
    }

    //등록된 글 수정
    @PutMapping("/posts/{id}")
    public Long updateBlog(@PathVariable Long id, @RequestBody PostRequestDto requestDto, UserDetailsImpl userDetails) {
        postService.update(id, requestDto, userDetails.getUser());
        return id;
    }



    // 등록된 글 삭제
    @DeleteMapping("/posts/{id}")
    public Long deleteBlog(@PathVariable Long id) {
        postService.detailPost(id);
        return id;
    }
}
