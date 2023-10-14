package com.longlive.kmong.DAO;

import com.longlive.kmong.DTO.ChatDTO;
import com.longlive.kmong.DTO.ChatListVo;
import com.longlive.kmong.DTO.ChatVO;
import com.longlive.kmong.DTO.RoomDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface Chat {
//    public List<RoomDTO> findAllRooms();
//    public long selectRoomId(String roomId);
    public Long createRoom(RoomDTO dto);

    public int selectUserId(String senderNickname);

    public void saveChat(ChatDTO chatDTO);

    public List<ChatVO>selectChatRoom(long roomId);

    public List<ChatListVo>selectChatList(long userId);

    public ChatDTO roomcheck(Map<String,Object> map);
}
