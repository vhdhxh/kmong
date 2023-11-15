package com.longlive.kmong.DTO;

import lombok.Data;
import lombok.ToString;

@Data
public class myBoardsDto {
    private Long board_Id;
    private String board_createTime;
    private String board_title;
    private String board_price;
}
