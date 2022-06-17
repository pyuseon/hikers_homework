package com.project.hikers_homework.repository;

import com.project.hikers_homework.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //수정일자 기준으로 찾아라 Desc : 역순으로
    List<Comment> findAllByPostIdOrderByModifiedAtDesc(Long id);
}
