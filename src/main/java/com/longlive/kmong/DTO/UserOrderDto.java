package com.longlive.kmong.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserOrderDto {
    private String orderId;
    private String orderTime;
    private String orderName;
    private String orderPrice;
    private String sellerName;
    private Long orderStatus;
    private Long boardId;

}
