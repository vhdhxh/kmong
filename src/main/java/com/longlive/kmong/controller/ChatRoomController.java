package com.longlive.kmong.controller;

import com.longlive.kmong.DTO.ChatDTO;
import com.longlive.kmong.DTO.ChatListVo;
import com.longlive.kmong.DTO.ChatVO;
import com.longlive.kmong.DTO.RoomDTO;
import com.longlive.kmong.config.auth.PrincipalDetails;
import com.longlive.kmong.service.ChatService;
import com.longlive.kmong.service.UserService;
import lombok.extern.log4j.Log4j2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.util.List;
//import com.longlive.kmong.DTO.ChatDTO;




@RestController
@RequiredArgsConstructor
@Log4j2
public class ChatRoomController {

    private final SimpMessagingTemplate template;
    private final ChatService chatService;
    private final UserService userService;

    /**
     * 채팅입력 및 결과반환
     *
     * @AuthenticationPrincipal PrincipalDetails principalDetails 은 Rest, Mvc 메서드에서 동작한다. MessageMapping에서는 동작하지 않는다.
     * @PathVariable도 마찬가지이다. @DestinationVariable을 사용하자.
     */
    @MessageMapping("/room/{roomId}/{nickname}")
    //StompConfig에 적힌 setApplicationDestinationPrefixes  /chat/send 제외해서 작성한다.
    public void message(@RequestBody ChatVO.Send chatVO, @DestinationVariable Long roomId, @DestinationVariable String nickname) throws Exception {
        //채팅을 치면 url에 적힌 roomId, nickname 과 클라이언트에서 받은 json데이터를 chatDTO 로받고 DB에 저장한다.
        System.out.println("메세지 입력을 받아서 컨트롤러로 넘어옴");
        System.out.println(chatVO.getSenderNickname() + "in controller");
        ChatDTO receive = chatService.save(roomId, chatVO, nickname);
        String receiverNickname = userService.selectReceiverNickname(receive.getReceiver_Id()); //

        receive.getMessage_content();

        template.convertAndSend("/chat/receive/" + receiverNickname, chatVO);
        template.convertAndSend("/chat/receive/room/" + roomId + "/" + nickname, chatVO);
        System.out.println("메시지를 topic 에 뿌림 message :" + receive.getMessage_content());
        // StompConfig에 적힌 enableSimpleBroker 도 포함해서 작성해야한다.
    }

    /**
     * 채팅방 만들기
     */
    @PostMapping("/chat/{nickname}")
    public ResponseEntity createRoom(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable String nickname, RoomDTO dto) {
        Long roomId = chatService.createRoom(principalDetails, nickname, dto);
        //만들어진 채팅방 roomId 를  매개변수에 넣어서 채팅방 인원들을 추가한다.
        System.out.println(roomId);
        return ResponseEntity.status(HttpStatus.OK).body(roomId);
    }


    /**
     * 채팅방 리스트 반환
     */
    @GetMapping("/chat/room/list")
    public ResponseEntity list(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<ChatListVo> list = chatService.list(principalDetails);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
//
    /**
     * 과거 대화내용 가져오기
     */
    @GetMapping("/chat/room/{roomId}")
    public ResponseEntity history(@PathVariable Long roomId){
        System.out.println(roomId);
        List<ChatVO> history = chatService.getHistory(roomId);
        System.out.println(history);
        return ResponseEntity.status(HttpStatus.OK).body(history);
    }


//    // 채팅 리스트 화면
//    @GetMapping("/rooms")
//    public ModelAndView rooms() {
//        log.info("# All Chat Rooms");
//        ModelAndView mv = new ModelAndView("chat/rooms");
//
//        mv.addObject("list", chatService.findAllRooms());
//
//        return mv;
//
//    }
//    //채팅방 개설
//    @PostMapping(value = "/room")
//    public String create(@RequestParam String name, RedirectAttributes rttr){
//
//        log.info("# Create Chat Room , name: " + name);
//        rttr.addFlashAttribute("roomName", chatService.createChatRoomDTO(name));
//        return "redirect:/chat/rooms";
//    }
//
//    //채팅방 조회
//    @GetMapping("/room")
//    public void getRoom(String roomId, Model model){
//
//        log.info("# get Chat Room, roomID : " + roomId);
//
//        model.addAttribute("room", chatService.findRoomById(roomId));
//    }

//    // 모든 채팅방 목록 반환
//    @GetMapping("/rooms")
//    @ResponseBody
//    public List<RoomDTO> room() {
//        return chatService.findAllRoom();
//    }
//    // 채팅방 생성
//    @PostMapping("/room")
//    @ResponseBody
//    public RoomDTO createRoom(@RequestParam String name) {
//        return chatService.createRoom(name);
//    }
//    // 채팅방 입장 화면
//    @GetMapping("/room/enter/{roomId}")
//    public String roomDetail(Model model, @PathVariable String roomId) {
//        model.addAttribute("roomId", roomId);
//        return "/chat/roomdetail";
//    }
//    // 특정 채팅방 조회
//    @GetMapping("/room/{roomId}")
//    @ResponseBody
//    public RoomDTO roomInfo(@PathVariable String roomId) {
//        return chatService.findById(roomId);
//    }

}
