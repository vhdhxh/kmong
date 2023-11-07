package com.longlive.kmong.DTO;

import lombok.Data;

@Data
public class ReviewDto {
    private Long userId;
    private Long boardId;
    private Long profileId;
    private String reviewContents;
    private String rating;

}
