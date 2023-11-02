package com.longlive.kmong.DTO;

import lombok.Data;

@Data
public class BoardListDto {
    private Long board_Id;
    private String board_title;
    private String board_thumbnail;
    private String board_price;

}
