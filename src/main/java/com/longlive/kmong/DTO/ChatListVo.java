package com.longlive.kmong.DTO;

import lombok.Data;

@Data
public class ChatListVo {
    private Long roomId;
    private String otherNickname;
    private String recentMessage;
    private String recentTime;
}
