package com.project.hikers_homework.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class CommentRequestDto {
    private final String contents;
    private final Long postId;

}