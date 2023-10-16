package com.longlive.kmong.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.longlive.kmong.DTO.MessageDto;
import com.longlive.kmong.DTO.SmsResponseDto;
import com.longlive.kmong.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@RestController
public class SmsController {

    private final SmsService smsService;

    @PostMapping("/sms/send")
    public SmsResponseDto sendSms(@RequestBody MessageDto messageDto) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        System.out.println(messageDto.getTo());
        SmsResponseDto responseDto = smsService.sendSms(messageDto);
        return responseDto;
    }
}