package com.longlive.kmong.DTO;

import lombok.Data;

@Data
public class CommentWriteDto {

    private Long boardId;
    private Long userId;
    private String contents;

}
