package com.longlive.kmong;

import com.longlive.kmong.DTO.UserDTO;
import com.longlive.kmong.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

@MybatisTest
public class mybatistest {


    @Autowired
    UserService userService;

}
