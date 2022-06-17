package com.project.hikers_homework.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.hikers_homework.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Post extends Timestamped{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

//    @Column(nullable = false)
//    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void update(PostRequestDto requestDto) {

        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

}

