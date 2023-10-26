package com.longlive.kmong.DAO;

import com.longlive.kmong.DTO.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface User {

    public long insertUser(UserDTO dto);
    public UserDTO selectUserEmail(String user_email);
    public UserDTO selectUserEmail2(Map<String,String> map);
    public List<UserDTO> selectUserAddress();
    public UserDTO selectUserName(String Name);
    public String selectReceiverNickname(int receive_Id);
    public boolean CheckEmailValidation(String user_email);
    public boolean SelectByNameAndPhoneAndEmail (Map<String,Object> map);
    public void passwordUpdate(Map<String,String> map);
    public void updateImage(Map<String,String> map);

   public void updateName(Map<String,Object> map);
   public void updateEmail(Map<String,String> map);
    public void updatePassword(Map<String,String> map);
    public void updateAddress(Map<String,Object> map );


}
