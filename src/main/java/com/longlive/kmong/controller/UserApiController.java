package com.longlive.kmong.controller;

import com.longlive.kmong.DTO.UserOrderDto;
import com.longlive.kmong.DTO.myBoardsDto;
import com.longlive.kmong.DTO.myCommentsDto;
import com.longlive.kmong.DTO.mySalesDto;
import com.longlive.kmong.config.auth.PrincipalDetails;
import com.longlive.kmong.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {
    private final CommentService commentService;
    private final ProfileService profileService;
    private final OrderService orderService;
    private final BoardService boardService;

    @GetMapping("/order")
    public ResponseEntity getOrder(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        List<UserOrderDto>orderList = orderService.getOrder(principalDetails.getDto().getUser_id());
        System.out.println(orderList);
        return ResponseEntity.status(HttpStatus.OK).body(orderList);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity getboardId(@PathVariable("orderId") String orderId) {
         String boardId = orderService.getboardId(orderId);
         String profileId = profileService.getProfileId(boardId);
        Map<String,String> map = new HashMap<>();
        map.put("boardId",boardId);
        map.put("profileId",profileId);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @PutMapping("/order")
    public void confirmOrder (Long orderId) {
        orderService.updateStatus(orderId);
    }

    @GetMapping("/myBoards")
    public List<myBoardsDto> myBoards (@RequestParam Long userId) {
       List<myBoardsDto> myBoard = boardService.myBoards(userId);
        return myBoard;
    }

    @GetMapping("/myComments")
    public List<myCommentsDto> myComments (@RequestParam Long userId) {
        List<myCommentsDto> myComment = commentService.myComments(userId);
        return myComment;
    }

    @GetMapping("/mySales")
    public List<mySalesDto> mySales (@RequestParam Long userId) {
        List<mySalesDto> mySale = orderService.mySales(userId);
        return mySale;
    }


}
