package com.longlive.kmong.controller;

import com.longlive.kmong.DTO.UserDTO;
import com.longlive.kmong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
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
    public String register(UserDTO dto) {
        System.out.println(dto);
           userService.insertUser(dto);
           return "/";
    }

    //로그인 폼 이동
    @GetMapping("/loginForm")
    public String login() {
        return "loginForm";
    }

    //로그인
    @PostMapping("/submit")
    public String submit (UserDTO dto) {

        return "main";
    }

}
