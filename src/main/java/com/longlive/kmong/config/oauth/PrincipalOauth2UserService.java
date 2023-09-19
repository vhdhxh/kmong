package com.longlive.kmong.config.oauth;

import com.longlive.kmong.DAO.User;
import com.longlive.kmong.DTO.UserDTO;
import com.longlive.kmong.config.auth.PrincipalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService  extends DefaultOAuth2UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private User user;


    // 구글로부터 받은 userRequest 데이터에 대한 후처리되는 메서드
    //함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest:"+userRequest);
        System.out.println("ClientRegistration:"+userRequest.getClientRegistration()); //registrationld로 어떤 OAuth로 로그인 했는지 확인 가능.
        System.out.println("AccessToken:"+userRequest.getAccessToken().getTokenValue());

        OAuth2User oauth2User = super.loadUser(userRequest);
        // 구글 로그인 버튼 클릭 -> 구글 로그인 창 -> 구글 로그인 완료 -> code를 리턴받고 (OAuth-clinet 라이브러리) - > AccessToken 요청
        // 여기까지가 user Request 정보 -> 회원프로필을 받아야함(loadUser함수를 통해 구글로부터 회원프로필 받음)
        System.out.println("Attributes:"+oauth2User.getAttributes());


        //회원가입을 강제로 진행해볼 예정
        String provider = userRequest.getClientRegistration().getClientId(); // google
        String providerId = oauth2User.getAttribute("sub");
        String user_email = provider + "_" + providerId;// google_
        String user_password = bCryptPasswordEncoder.encode("겟인데어");

       UserDTO userEntity = user.selectUserEmail(user_email);
       if(userEntity ==null) {
      userEntity = UserDTO.builder()
              .user_email(user_email)
              .user_password(user_password)
              .provider(provider)
              .providerId(providerId)
              .build();
      user.insertUser(userEntity);
       } else {

       }

        return new PrincipalDetails(userEntity,oauth2User.getAttributes());
    }
}
