package com.longlive.kmong.config.oauth;

import com.longlive.kmong.DAO.User;
import com.longlive.kmong.DTO.UserDTO;
import com.longlive.kmong.config.auth.PrincipalDetails;
import com.longlive.kmong.config.oauth.provider.GoogleUserInfo;
import com.longlive.kmong.config.oauth.provider.NaverUserInfo;
import com.longlive.kmong.config.oauth.provider.OAuth2UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

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
        OAuth2UserInfo oAuth2UserInfo = null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            System.out.println("구글 로그인 요청");
            oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
        }else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            System.out.println("네이버 로그인 요청");
            oAuth2UserInfo = new NaverUserInfo((Map)oauth2User.getAttributes().get("response"));
        }
        String provider = oAuth2UserInfo.getProvider(); // google
        String providerId = oAuth2UserInfo.getProviderId();
        String user_email = oAuth2UserInfo.getEmail();// google_
        String user_password = bCryptPasswordEncoder.encode("겟인데어");
        String user_name = oAuth2UserInfo.getName();

       UserDTO userEntity = user.selectUserEmail(user_email);

       if(userEntity ==null) {
           System.out.println("OAuth 로그인이 최초입니다.");
      userEntity = UserDTO.builder()
              .user_email(user_email)
              .user_password(user_password)
              .user_name(user_name)
              .provider(provider)
              .providerId(providerId)
              .build();
      user.insertUser(userEntity);
       } else {
           System.out.println("당신은 로그인 한적이 있습니다.");
       }
        System.out.println(userRequest.getClientRegistration().getRegistrationId());
        System.out.println(oAuth2UserInfo.getName());

        return new PrincipalDetails(userEntity,oauth2User.getAttributes());
    }
}
