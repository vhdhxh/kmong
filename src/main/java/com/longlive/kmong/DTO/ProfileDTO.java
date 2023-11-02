package com.longlive.kmong.DTO;

import lombok.Data;

@Data
public class ProfileDTO {
    private long user_Id;
    private String profile_introduce; // 한줄 소개
    private String profile_service;  // 제공 서비스
    private String profile_address;  // 활동 지역
    private String user_name;     // 이름(활동명)
    private String profile_time;  // 연락가능 시간
    private String profile_deployee; // 직원 수
    private String profile_detail; //상세서비스


}
