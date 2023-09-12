package com.longlive.kmong.config.auth;

import com.longlive.kmong.DAO.User;
import com.longlive.kmong.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//시큐리티 설정에서 loginProcecssing url("/login");
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 빈등록이 되어있는 loadUserByUsername 함수가 실행

@Service
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired
    private User user;
  //  private UserDTO dto;
    @Override
    public UserDetails loadUserByUsername(String user_email) throws UsernameNotFoundException {
        System.out.println(user_email);
        System.out.println(user.selectUserEmail(user_email));
           UserDTO userEntity = user.selectUserEmail(user_email);
           if (userEntity !=null) {
               return new PrincipalDetails(userEntity);
           }
        return null;
    }
}
