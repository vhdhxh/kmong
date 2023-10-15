package com.longlive.kmong.DAO;

import com.longlive.kmong.DTO.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface User {

    public void insertUser(UserDTO dto);
    public UserDTO selectUserEmail(String user_email);
    public void selectUserEmail2(String user_email);
    public List<UserDTO> selectUserAddress();
    public UserDTO selectUserName(String Name);
    public String selectReceiverNickname(int receive_Id);
    public boolean CheckEmailValidation(String user_email);


}
