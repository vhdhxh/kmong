package com.longlive.kmong.DAO;

import com.longlive.kmong.DTO.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface User {

    public void insertUser(UserDTO dto);
    public UserDTO selectUserEmail(String user_email);
    public void selectUserEmail2(String user_email);
    public List<UserDTO> selectUserAddress();
    public UserDTO selectUserName(String Name);
    public String selectReceiverNickname(int receive_Id);
    public boolean CheckEmailValidation(String user_email);
    public boolean SelectByNameAndPhoneAndEmail (Map<String,Object> map);
    public void passwordUpdate(Map<String,String> map);

}
