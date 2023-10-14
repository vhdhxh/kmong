package com.longlive.kmong.service;


//import com.longlive.kmong.DAO.Chat;
import com.longlive.kmong.DAO.Chat;
import com.longlive.kmong.DAO.User;
//import com.longlive.kmong.DTO.ChatDTO;
import com.longlive.kmong.DTO.*;
import com.longlive.kmong.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final Chat chat;
//    private final User user;
//    private final ChatDTO chatDTO;


    /**
     * 채팅 내용 저장
     */
    public ChatDTO save(Long roomId, ChatVO.Send chatVO, String nickname) {
        //보낸사람 닉네임 (나) 를 받아서 select 해서 보낸사람의 user_Id를 받는다
        System.out.println(chatVO.getSenderNickname());
       int sender_Id = chat.selectUserId(chatVO.getSenderNickname());
        System.out.println(sender_Id);
       //url 에있는 채팅상대방 닉네임을 받아서 join select 해서 받는사람의 user_Id를 받는다
        System.out.println("상대닉네임 : " + nickname);

       int receiver_Id = chat.selectUserId(URLDecoder.decode(nickname));
       String message = chatVO.getMessage();
        ChatDTO chat = ChatDTO.builder()
                .room_Id(roomId)
                .sender_Id(sender_Id)
                .receiver_Id(receiver_Id)
                .message_content(message)
                .build();

        this.chat.saveChat(chat);


        return chat;
    }


//    public ChatDTO.Receive save(Long roomId, ChatDTO.Send chatDTO, String nickname) {
//        ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElse(null);
//        String dNickname = URLDecoder.decode(nickname, StandardCharsets.UTF_8);
//        String dSenderNick = URLDecoder.decode(chatDTO.getSenderNickname(), StandardCharsets.UTF_8);
//        Member receiver = memberRepository.findByNickname(dNickname).orElse(null);
//        Member sender = memberRepository.findByNickname(dSenderNick).orElse(null);
//
//        Chat chat = Chat.builder()
//                .sender(sender)
//                .receiver(receiver)
//                .message(chatDTO.getMessage())
//                .chatRoom(chatRoom).build();
//        Chat save = chatRepository.save(chat);
//
//        ChatDTO.Receive receive = ChatDTO.entityToDTO(chat);
//        return receive;
//    }

    /**
     * 채팅방 만들기
     */
    public Long createRoom(PrincipalDetails principalDetails, String nickname,RoomDTO dto) {
        //닉네임을 조회해서 나와 그사람이 채팅을 친 이력이 있다면 , 그 채팅방 번호를 리턴하고, 없다면 새로운 채팅방을 만들어 리턴한다.
        long myId = principalDetails.getDto().getUser_id();
        long yourId = chat.selectUserId(nickname);
        //sql mapper 에 2개 이상의 파라미터 전달을 위해 map 사용
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("myId", myId);
        map.put("yourId",yourId);
        System.out.println(map);

        ChatDTO roomcheck = chat.roomcheck(map);
        System.out.println("룸체크 : " +roomcheck);

        if (roomcheck!=null)
            return roomcheck.getRoom_Id();
        else {


            long roomIda = chat.createRoom(dto);
            long roomId = dto.getRoom_Id();


            //  long roomId = chat.selectRoomId("1");
            return roomId;
        }
    }


//        UserDTO userDTO = principalDetails.getDto();
//        String dNickname = URLDecoder.decode(nickname, StandardCharsets.UTF_8);
//        UserDTO otheruser = user.selectUserName(dNickname);

//         chatDTO = chat.findByReceiverAndSender(member.getId(), otherMember.getId()).orElse(null);
//
//        if(chat == null){
//            ChatRoom chatRoom = new ChatRoom();
//            ChatRoom save = chat.save(chatRoom);
//            return save.getId();
//        }else{
//            return chat.getChatRoom().getId();
//        }
//    }


    /**
     * 채팅 목록 반환
     * @param principalDetails
     * @return
     */
    public List<ChatListVo> list(PrincipalDetails principalDetails) {
        UserDTO userDTO = principalDetails.getDto();
        List<ChatListVo> list = chat.selectChatList(userDTO.getUser_id());
        return list;
    }

    /**
     * 과거 대화내용 반환
     * @param roomId
     * @return
     */
    public List<ChatVO> getHistory(Long roomId) {

        List<ChatVO> collect = chat.selectChatRoom(roomId);
        return collect;
    }
//}


}