package com.longlive.kmong.DTO;

import lombok.Data;

@Data
public class CommentListDto {
    private String commentCreateTime;
    private String commentWriter;
    private String commentContents;
    private Long commentRid;
    private Long boardId;
    private Long commentDepth;
    private Long commentId;
    private Long status;

}
