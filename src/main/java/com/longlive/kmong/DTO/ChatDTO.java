package com.longlive.kmong.DTO;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

//@Data
//public class ChatDTO {
//   private int message_Id;
//    private int user_Id;
//    private int room_Id;
//   private String message_content;
//   private Timestamp send_Date;

//
//    @Getter
//    @ToString
//    public static class Send{
//        private String senderNickname;
//        private String message;
//    }
//
//    @Getter
//    @ToString
//    public static class Receive{
//        private Long roomId;
//        private String receiverNickname;
//        private String senderNickname;
//        private String message;
//        private String sendTime;
//    }
//
//
//
//    @Getter
//    @ToString
//    public static class List{
//        private Long roomId;
//        private String otherNickname;
//        private String recentMessage;
//        private String recentTime;
//    }
//
//
//    }

@Data
@Builder
public class ChatDTO {
    private int message_Id;
    private int receiver_Id;
    private long room_Id;
    private int sender_Id;
    private String message_content;
    private LocalDateTime sendDate;



}
