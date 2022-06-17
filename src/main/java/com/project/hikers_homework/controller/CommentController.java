package com.project.hikers_homework.controller;

import com.project.hikers_homework.dto.CommentRequestDto;
import com.project.hikers_homework.dto.CommentResponseDto;
import com.project.hikers_homework.model.Comment;
import com.project.hikers_homework.repository.CommentRepository;
import com.project.hikers_homework.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor // Repository 와 service 둘다 불러와 !
@RestController
public class CommentController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;


    // 댓글 생성하기
    @PostMapping("/write")
    // @RequestBody 요청이 들어있는 바디에 들어있는 친구를 가져와라
    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto){
        //        return postRepository.save(post);
        return commentService.registerComment(requestDto);
    }

    // 댓글 조회하기
    @GetMapping("/comment/{postId}")
    public List<Comment> showComments(@PathVariable Long postId) {
        return commentRepository.findAllByPostIdOrderByModifiedAtDesc(postId);
    }

    // 댓글 수정하기
    @PutMapping("/commenting/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.update(id, requestDto);
    }

    // 댓글 삭제하기
    @DeleteMapping("/commenting/{id}")
    public Long deletePost(@PathVariable Long id){
        commentRepository.deleteById(id);
        return id;
    }


}
