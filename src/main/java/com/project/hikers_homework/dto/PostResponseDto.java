package com.project.hikers_homework.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class PostResponseDto {

    private final String title;
    private final String contents;
    private LocalDateTime createdAt;
    private Long commentNum;

}