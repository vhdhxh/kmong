package com.longlive.kmong.controller;

import com.longlive.kmong.DTO.UserDTO;
import com.longlive.kmong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
@Autowired
private UserService userService;

    //회원가입 폼 이동
    @GetMapping("/sign-up")
     public String signup () {
        System.out.println("dd");
        return "sign-up";
    }
    //회원가입
    @PostMapping("/register")
    public void register(UserDTO dto) {
           userService.insertUser(dto);
    }

    //로그인 폼 이동
    @GetMapping("/log-in")
    public String login() {
        return "log-in";
    }

    //로그인

}
