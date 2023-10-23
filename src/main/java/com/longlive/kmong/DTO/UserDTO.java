package com.longlive.kmong.DTO;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class UserDTO {

   @Builder
   public UserDTO(String user_email, String user_password, String user_name, String user_address, String user_phonenum
           , String user_gender, String provider, String providerId,String user_image) {
      this.user_email = user_email;
      this.user_password = user_password;
      this.user_name = user_name;
      this.user_address = user_address;
      this.user_phonenum = user_phonenum;
      this.user_gender = user_gender;
      this.provider = provider;
      this.providerId = providerId;
      this.user_image = user_image;

   }

   private int user_id;
   @NotBlank(message = "이메일는 필수 입력사항 입니다.")
   @Email(message = "이메일 형식을 맞춰주세요")
   private String user_email;
   @NotBlank(message = "비밀번호는 필수 입력사항 입니다.")
   @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\\\d)[a-zA-Z\\\\d]{8,16}$" , message = "비밀번호는 영어와 숫자로 포함해서 8~16자리 이내로 입력해주세요")
   private String user_password;
   @NotBlank(message = "이름은 필수 입력사항 입니다.")
   private String user_name;
   private String user_address;

   private String user_phonenum;
   private String user_gender;

   private String provider;
   private String providerId;
   private String user_image;

}
