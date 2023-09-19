package com.longlive.kmong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class KmongApplication {
	@Bean  //해당메서드의 리턴되는 오브젝트를 빈으로 등록해준다.
	public BCryptPasswordEncoder encodePwd() { //회원가입 비밀번호 암호화를 위해 빈으로 등록
		return new BCryptPasswordEncoder();
	}


	public static void main(String[] args) {
		SpringApplication.run(KmongApplication.class, args);
	}

}
