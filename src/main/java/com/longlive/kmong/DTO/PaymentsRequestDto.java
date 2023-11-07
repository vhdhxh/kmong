package com.longlive.kmong.DTO;

import lombok.Data;

@Data
public class PaymentsRequestDto {
    private String userId;
    private String method;
    private String amount;
    private String boardId;
}
