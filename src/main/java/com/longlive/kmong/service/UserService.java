package com.longlive.kmong.service;

import com.longlive.kmong.DAO.User;
import com.longlive.kmong.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    User user;


    public void insertUser(UserDTO dto) { // 회원가입

        user.insertUser(dto);
    }
}