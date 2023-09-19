package com.longlive.kmong.config;

import com.longlive.kmong.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

@Configuration
@EnableWebSecurity  // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.    @EnableWebSecurity 애너테이션은 Spring Security와 함께 웹 애플리케이션에서 보안 구성을 활성화하기 위해 사용된다.
public class SecurityConfig  {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // 인가(접근권한) 설정
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()  //이외의 url은 허가한다
                .and()
                // 로그인 설정
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")  // /login 이라는 url이 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해 준다.
                .usernameParameter("user_email")
                .passwordParameter("user_password")
                .defaultSuccessUrl("/") //로그인이 성공하면 / 로 가게한다.
                .and()
                // oauth2 로그인 설정
                .oauth2Login()
                .loginPage("/loginForm") //구글 로그인이 된후 후처리필요. 1.코드받기(인증),2.엑세스토큰(권한),3.사용자 프로필 정보를 가져오고 4. 그정보를 토대로 회원가입을 자동으로 진행시키기도 함
                .userInfoEndpoint()                           // 4-2(이메일,전화번호,이름,아이디) 쇼핑몰-> (집주소), 백화점몰 -> (vip등급,일반등급)
                .userService(principalOauth2UserService);                           //Tip. 구글로그인 완료후 (엑세스토큰+ 사용자프로필정보 를 받는다)

         return http.build();
    }
//    @Bean  //해당메서드의 리턴되는 오브젝트를 빈으로 등록해준다.
//    public BCryptPasswordEncoder encodePwd() { //회원가입 비밀번호 암호화를 위해 빈으로 등록
//        return new BCryptPasswordEncoder();
//    } 순환참조 에러가 발생하여 kmongAplication 으로 옮김
}
