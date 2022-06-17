package com.project.hikers_homework.service;

import com.project.hikers_homework.dto.CommentRequestDto;
import com.project.hikers_homework.dto.CommentResponseDto;
import com.project.hikers_homework.model.Comment;
import com.project.hikers_homework.model.Post;
import com.project.hikers_homework.repository.CommentRepository;
import com.project.hikers_homework.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class CommentService {
    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {

        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    public CommentResponseDto registerComment(CommentRequestDto requestDto) {
        // DB 내부 portfolio 확인
        Post post = postPresentCheck(requestDto.getPostId(), postRepository);


        Comment comment = Comment.commentBuilder()
                .contents(requestDto.getContents())
                .post(post).build();


        commentRepository.save(comment);

        return CommentResponseDto.builder().commentId(comment.getId()).build();
    }


    public static Post postPresentCheck(Long id, PostRepository repository){
        Optional<Post> optionalPost = repository.findById(id);
        if(!optionalPost.isPresent()){
            throw new NullPointerException("No Post is found with ID : "+ id);
        }

        return optionalPost.get();
    }


    @Transactional // 업데이트에 꼭 반영 해줘야 된다 ! (코멘트 수정하기)
    public Long update(Long id, CommentRequestDto requestDto) {// public 반환값 update(재료)
        // NullPointerException :  내가 가르키는 것이 없다.
        // IllegalArgumentException : 내가 찾는것이 잘못되었다.
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        comment.update(requestDto.getContents());
        return id;
    }
}
