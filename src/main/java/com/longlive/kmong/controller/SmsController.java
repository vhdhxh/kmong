package com.longlive.kmong.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.longlive.kmong.DTO.MessageDto;
import com.longlive.kmong.DTO.SmsResponseDto;
import com.longlive.kmong.DTO.UserDTO;
import com.longlive.kmong.service.SmsService;
import com.longlive.kmong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class SmsController {

    private final SmsService smsService;
    private final UserService userService;

    @PostMapping("/sms/send")
    @ResponseBody
    public ResponseEntity sendSms(@RequestBody Map<String, String> findMap) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        System.out.println(findMap.get("email"));
        String email = (findMap.get("email"));
        String name = (findMap.get("name"));
        String phone = (findMap.get("phone"));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("email", email);
        map.put("name", name);
        map.put("phone", phone);

        boolean exists = userService.SelectByNameAndPhoneAndEmail(map);
        // 여기서 userService 는 데이터베이스나 다른 저장소에서 사용자 정보를 조회하는 로직을 처리합니다.
        System.out.println(exists);
        if (exists) {
            MessageDto messageDto = new MessageDto(findMap.get("phone"));
            SmsResponseDto responseDto = smsService.sendSms(messageDto);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("badrequest");
        }
    }

    @PostMapping("/sms/auth")
    @ResponseBody
    public ResponseEntity smsAuth(@RequestBody Map<String, String> num, Model model) {
        System.out.println("num.get(num) = " + num.get("num"));
        String num1 = smsService.dd(num.get("num"));
        System.out.println(num1);
        if (num1 != null) {
            return ResponseEntity.status(HttpStatus.OK).body("ok");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("badrequest");
        }
    }

    @PostMapping("/sms/pwfind")
    public String pwfind(@RequestParam("verificationCode") String verificationCode,@RequestParam("user_email") String email, Model model,UserDTO userDTO) {
        System.out.println("컨트롤러");
        String phone = smsService.dd(verificationCode);
        if (phone != null) {
            model.addAttribute("user_email", email);
            System.out.println("notnull");
            return "updatepw";
        } else {

            System.out.println("null5");
            return "redirect:/sms/findpw?error=true";
        }
    }
    @GetMapping("/sms/findpw")
    public String findpw (@RequestParam(value = "error",required = false)boolean error, Model model) {
        if (error) {
            model.addAttribute("errorMessage", "인증번호가 틀렸습니다.");
        }
        return "findpw";
    }

    @PostMapping("/pwupdate")
    public String pwasswordUpdate(@RequestParam("pw") String pw, @RequestParam("email") String email) {
        System.out.println("pw ="+pw);
        Map<String , String> map = new HashMap<>();

        map.put("password",pw);
        map.put("email", email);
        userService.passwordUpdate(map);
        return "main";
    }

//    @PostMapping("/sms/pwfind")
//    public String pwfind (@RequestBody Map<String, String> email, Model model) {
//        System.out.println("email.get(email) = " + email.get("email"));
//        model.addAttribute("email",email.get("email"));
//        return "updatepw";
//    }
    }

