package com.longlive.kmong.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BoardDTO {
    private Long board_Id;
    private String board_contents;
    private String board_title;
    private String board_thumbnail;
    private String board_price;
    private Long user_Id;
    private LocalDateTime board_createtime;




}
