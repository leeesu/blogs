package com.sparta.blogs.model;

import com.sparta.blogs.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;


    public Post(PostRequestDto requestDto, User user) {
        this.user = user;
        this.writer = getWriter();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void update(PostRequestDto requestDto){
        this.writer = requestDto.getWriter();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
