package com.longlive.kmong.DTO;

import lombok.*;

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
   private String user_email;
   private String user_password;
   private String user_name;
   private String user_address;
   private int user_phonenum;
   private String user_gender;

   private String provider;
   private String providerId;

}
