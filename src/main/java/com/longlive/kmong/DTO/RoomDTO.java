package com.longlive.kmong.DTO;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class RoomDTO {
    private Long room_Id;
    private LocalDateTime created_time;
}
