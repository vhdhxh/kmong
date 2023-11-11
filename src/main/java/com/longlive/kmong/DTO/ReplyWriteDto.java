package com.longlive.kmong.DTO;

import lombok.Data;

@Data
public class ReplyWriteDto {
    private Long boardId;
    private Long userId;
    private String contents;
    private Long commentRid;
}
