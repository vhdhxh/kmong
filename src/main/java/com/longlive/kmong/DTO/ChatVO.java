package com.longlive.kmong.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
public class ChatVO {
    private Long roomId;
    private String receiverNickname;
    private String senderNickname;
    private String message;
    private String sendTime;

    @Getter
    @ToString
    public static class Send{
        private String senderNickname;
        private String message;
    }

    @Getter
    @ToString
    public static class Receive{
        private Long roomId;
        private String receiverNickname;
        private String senderNickname;
        private String message;
        private String sendTime;
    }
    @Getter
    @ToString
    public static class List{
        private Long roomId;
        private String otherNickname;
        private String recentMessage;
        private String recentTime;
    }


    }

