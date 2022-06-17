package com.project.hikers_homework.repository;

import com.project.hikers_homework.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();

    @Query("select p from Post p left join fetch p.comments where p.id = :id")
    Optional<Post> findPostCommentFetchJoin(@Param("id") Long id);
}


