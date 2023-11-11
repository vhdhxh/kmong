package com.longlive.kmong.DTO;

import lombok.Data;

@Data
public class CommentDto {
    private Long boardId;
    private Long commentId;
    private String content;
}
