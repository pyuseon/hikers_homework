package com.project.hikers_homework.controller;


import com.project.hikers_homework.dto.PostRequestDto;
import com.project.hikers_homework.dto.PostResponseDto;
import com.project.hikers_homework.model.Comment;
import com.project.hikers_homework.model.Post;
import com.project.hikers_homework.repository.PostRepository;
import com.project.hikers_homework.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // Repository 와 service 둘다 불러와 !
@RestController
public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;


    @PostMapping("/posting")
    // @RequestBody 요청이 들어있는 바디에 들어있는 친구를 가져와라
    public Post createPost(@RequestBody PostRequestDto requestDto){

        // 로그인 되어있는 회원 테이블의 ID
        Post post = new Post(requestDto);
        return  postRepository.save(post);
    }

    // 전체 포스트 가져오기
    @GetMapping("/posts")
    public List<Post> readPost(){
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    // 게시물 하나만 조회
    @GetMapping ("/posts/detail/{id}")
    public PostResponseDto showOnePost(@PathVariable Long id) {
        Post post;
        System.out.println(id);
//        post = postRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("찾는 게시글이 없습니다.")
//        );

        post = postRepository.findPostCommentFetchJoin(id).orElseThrow(
                () -> new IllegalArgumentException("찾는 게시글이 없습니다.")
        );

        List<Comment>commentList = post.getComments();
        long commentNum = commentList.size();

        PostResponseDto responseDto = PostResponseDto.builder()
                .contents(post.getContents())
                .commentNum(commentNum)
                .title(post.getTitle())
                .createdAt(post.getCreatedAt()).build();


        return responseDto;
    }

    // 게시물 수정하기
    @PutMapping("/posting/detail/{id}")
    public Long updatePost(@PathVariable Long id,
                           @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    // 게시물 삭제하기
    @DeleteMapping("/posting/detail/{id}")
    public Long deletePost(@PathVariable Long id){
        postRepository.deleteById(id);
        return id;
    }

}
