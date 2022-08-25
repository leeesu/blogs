package com.sparta.blogs.service;

import com.sparta.blogs.dto.PostRequestDto;
import com.sparta.blogs.model.Post;
import com.sparta.blogs.model.User;
import com.sparta.blogs.repository.PostRepository;
import com.sparta.blogs.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    //토큰없이도 쓸수있는 구역
    private final PostRepository postRepository;

    @Transactional
    public Post createPost(PostRequestDto requestDto, UserDetailsImpl userDetails) {
        Post post = new Post(requestDto, userDetails.getUser());
        postRepository.save(post);
        return post;
    }

    @Transactional
    public List<Post> listPost() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Optional<Post> detailPost(Long id) {
        return postRepository.findById(id);
    }


    @Transactional
    public Long update(Long id, PostRequestDto requestDto, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        if (post.getUser().getUsername().equals(user.getUsername())) {
            post.update(requestDto);
            return id;
        } else {
            System.out.println("아이디가 존재하지 않습니다.");
            return null;
        }
    }





    @Transactional
    public Long delete(Long id, PostRequestDto requestDto, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        if(post.getUser().getUsername().equals(user.getUsername())) {
            postRepository.deleteById(id);
            return id;

        }
        System.out.println("게시글을 삭제 할 수 없습니다.");
        return null;
    }
}
