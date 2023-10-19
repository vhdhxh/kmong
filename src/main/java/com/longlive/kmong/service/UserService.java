package com.longlive.kmong.service;

import com.longlive.kmong.DAO.User;
import com.longlive.kmong.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    User user;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입
    public void insertUser(UserDTO dto) {
        user.insertUser(dto);
    }

    // 로그인
    public UserDTO getUser() {
        return user.selectUserEmail("vhdhxh@naver.com");
    }

    public List<UserDTO> getAddress() {
        return user.selectUserAddress();
    }

    public String selectReceiverNickname(int receive_Id) {
       return user.selectReceiverNickname(receive_Id);
    }

    public boolean CheckEmailValidation(String user_email) {
        return user.CheckEmailValidation(user_email);
    }

    public boolean SelectByNameAndPhoneAndEmail (Map<String,Object> map){
        return user.SelectByNameAndPhoneAndEmail(map);
    }
   public void passwordUpdate(Map<String,String> map){


       String rawPassword1 = map.get("password");
       String encPassword1 = bCryptPasswordEncoder.encode(rawPassword1);
       map.put("password",encPassword1);
       user.passwordUpdate(map);
   }



}