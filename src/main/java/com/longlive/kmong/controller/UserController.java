package com.longlive.kmong.controller;

import com.longlive.kmong.DTO.UserDTO;
import com.longlive.kmong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
@Autowired
private UserService userService;

@Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

    //회원가입 폼 이동
    @GetMapping("/sign-up")
     public String signup () {
        System.out.println("dd");
        return "sign-up";
    }
    //회원가입
    @PostMapping("/register")
    public String register(UserDTO dto) {

         String rawPassword = dto.getUser_password();   //회원가입시 입력받은 비밀번호를 저장
         String encPassword = bCryptPasswordEncoder.encode(rawPassword); //저장한 비밀번호를 암호화
         dto.setUser_password(encPassword);
        userService.insertUser(dto);
           return "/";
    }

    //로그인 폼 이동
    @GetMapping("/loginForm")
    public String login() {
        System.out.println(userService.getUser());
        return "loginForm";
    }

    //로그인
    @PostMapping("/submit")
    public String submit (UserDTO dto) {

        return "main";
    }

}
