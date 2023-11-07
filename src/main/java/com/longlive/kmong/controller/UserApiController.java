package com.longlive.kmong.controller;

import com.longlive.kmong.DTO.UserOrderDto;
import com.longlive.kmong.config.auth.PrincipalDetails;
import com.longlive.kmong.service.BoardService;
import com.longlive.kmong.service.OrderService;
import com.longlive.kmong.service.ProfileService;
import com.longlive.kmong.service.UserService;
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
    private final UserService userService;
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


}
