package com.project.hikers_homework.dto;

import lombok.Builder;
import lombok.Data;

// Getter, Setter기능 통합
@Data
@Builder
public class CommentResponseDto {
    private Long commentId;
}
