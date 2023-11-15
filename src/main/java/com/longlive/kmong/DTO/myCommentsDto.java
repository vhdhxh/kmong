package com.longlive.kmong.DTO;

import lombok.Data;

@Data
public class myCommentsDto {
    private Long comment_Id;
    private String comment_createTime;
    private String comment_contents;
    private String boardTitle;
}
