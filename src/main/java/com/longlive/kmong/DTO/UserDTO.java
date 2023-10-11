package com.longlive.kmong.DTO;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UserDTO {

   @Builder
   public UserDTO(String user_email, String user_password, String user_name, String user_address, int user_phonenum
           , String user_gender, String provider, String providerId) {
      this.user_email = user_email;
      this.user_password = user_password;
      this.user_name = user_name;
      this.user_address = user_address;
      this.user_phonenum = user_phonenum;
      this.user_gender = user_gender;
      this.provider = provider;
      this.providerId = providerId;
   }

   private int user_id;
   @NotBlank(message = "아이디는 필수 입력사항 입니다.")
   private String user_email;
   @NotBlank(message = "비밀번호는 필수 입력사항 입니다.")
   private String user_password;
   @NotBlank(message = "이름은 필수 입력사항 입니다.")
   private String user_name;
   private String user_address;

   private int user_phonenum;
   private String user_gender;

   private String provider;
   private String providerId;

}
