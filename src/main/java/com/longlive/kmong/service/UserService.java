package com.longlive.kmong.service;

import com.longlive.kmong.DAO.User;
import com.longlive.kmong.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    User user;

    // 회원가입
    public void insertUser(UserDTO dto) {
        user.insertUser(dto);
    }

    // 로그인
    public UserDTO getUser() {
        return user.selectUserEmail("vhdhxh@naver.com");
    }
}