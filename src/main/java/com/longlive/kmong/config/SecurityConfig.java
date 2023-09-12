package com.longlive.kmong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
public class SecurityConfig  {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()  //이외의 url은 허가한다
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")  // /login 이라는 url이 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해 준다.
                .usernameParameter("user_email")
                .passwordParameter("user_password")
                .defaultSuccessUrl("/"); //로그인이 성공하면 / 로 가게한다.

         return http.build();
    }
    @Bean  //해당메서드의 리턴되는 오브젝트를 빈으로 등록해준다.
    public BCryptPasswordEncoder encodePwd() { //회원가입 비밀번호 암호화를 위해 빈으로 등록
        return new BCryptPasswordEncoder();
    }
}
