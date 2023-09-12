package com.longlive.kmong;

import com.longlive.kmong.DAO.User;
import com.longlive.kmong.DTO.UserDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class mybatistest {

    @Test
    public void testSelectUserEmail() {
        // 가상의 사용자 이메일
        String userEmail = "abcd123@naver.com";

        // 가상의 사용자 정보
        UserDTO expectedUser = new UserDTO();
        expectedUser.setUser_id(1);
        expectedUser.setUser_email("abcd123@naver.com");

        // Mock 객체 생성 및 설정
        User userDAO = mock(User.class);

        // selectUserEmail 메서드 호출 시 가상의 사용자 정보 반환하도록 설정
        when(userDAO.selectUserEmail(userEmail)).thenReturn(expectedUser);

        // 테스트 대상 메서드 호출
        UserDTO actualUser = userDAO.selectUserEmail(userEmail);

        // 결과 검증
        assertEquals(expectedUser, actualUser);
    }
}
