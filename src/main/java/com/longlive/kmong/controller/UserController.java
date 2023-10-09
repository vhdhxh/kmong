package com.longlive.kmong.controller;

import com.longlive.kmong.DTO.UserDTO;
import com.longlive.kmong.config.auth.PrincipalDetails;
import com.longlive.kmong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
@Autowired
private UserService userService;

@Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

@GetMapping("/test/login")
public @ResponseBody String testLogin(Authentication authentication //의존성주입
        , @AuthenticationPrincipal PrincipalDetails userDetails) {
    System.out.println("/test/login =================");
    PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
    System.out.println("authentication:" + principalDetails.getDto());

    System.out.println("userDetails:" + userDetails.getDto());
    return "세션 정보확인하기";
}

    @GetMapping("/test/oauth/login")
    public @ResponseBody String testOAuthLogin(Authentication authentication //의존성주입
         ,@AuthenticationPrincipal OAuth2User oauth) {
        System.out.println("/test/login =================");
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        System.out.println("authentication:" + oAuth2User.getAttributes());
        System.out.println("oauth2User:"+oauth.getAttributes());

        return "세션 정보확인하기";
    }

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

        return "loginForm";
    }

    //로그인
    @PostMapping("/submit")
    public String submit (UserDTO dto) {

        return "main";
    }

    // OAuth 로그인을 해도 PrincipalDetails
    // 일반 로그인을 해도 PrincipalDetails
    @GetMapping("/user")
    public @ResponseBody String user (@AuthenticationPrincipal PrincipalDetails principalDetails,Authentication authentication) {
        System.out.println("principalDetails:" + principalDetails.getDto());
        System.out.println("authentication:" + authentication.getPrincipal());
        System.out.println(principalDetails.getUsername());
        return "user";
    }

    @GetMapping("/auth/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model) {

        /* 에러와 예외를 모델에 담아 view resolve */
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "error";
    }
}
