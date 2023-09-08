package com.longlive.kmong.DAO;

import com.longlive.kmong.DTO.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface User {

    public void insertUser(UserDTO dto);
    public void getUser(UserDTO dto);
}
