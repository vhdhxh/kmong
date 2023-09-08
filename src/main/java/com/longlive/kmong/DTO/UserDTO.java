package com.longlive.kmong.DTO;

import lombok.*;

@Data
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class UserDTO {

   private String user_id;
   private int user_password;
   private String user_name;
   private String user_address;
   private int user_phonenum;
   private String user_gender;
   private String user_nickname;
}
