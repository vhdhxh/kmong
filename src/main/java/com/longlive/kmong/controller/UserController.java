package com.longlive.kmong.controller;

import com.longlive.kmong.DTO.UserDTO;
import com.longlive.kmong.config.auth.PrincipalDetails;
import com.longlive.kmong.service.ProfileService;
import com.longlive.kmong.service.UserService;
import com.longlive.kmong.validator.CheckEmailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;



@Controller
@RequiredArgsConstructor
public class UserController {
@Autowired
private UserService userService;

@Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;
   private final CheckEmailValidator checkEmailValidator;
   private final ProfileService profileService;
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkEmailValidator);
    }
@GetMapping("/test/login")
public @ResponseBody String testLogin(Authentication authentication //의존성주입
        , @AuthenticationPrincipal PrincipalDetails userDetails) {
    System.out.println("/test/login =================");
    PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
    System.out.println("authentication:" + principalDetails.getDto());

    System.out.println("userDetails:" + userDetails.getDto());
    return "세션 정보확인하기";
}

    @GetMapping("/test/oauth/login")
    public @ResponseBody String testOAuthLogin(Authentication authentication //의존성주입
         ,@AuthenticationPrincipal OAuth2User oauth) {
        System.out.println("/test/login =================");
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        System.out.println("authentication:" + oAuth2User.getAttributes());
        System.out.println("oauth2User:"+oauth.getAttributes());

        return "세션 정보확인하기";
    }

//    회원가입 폼 이동
    @GetMapping("/sign-up")
     public String signup (UserDTO userDTO) {
        System.out.println("dd");
        return "sign-up";
    }
    //회원가입

//    @GetMapping("register")
//    public void register(UserDTO userDTO) {
//        System.out.println("get");
//        // 여기서 UserDTO를 받아줘야 회원가입 실패시 그 입력값이 그대로 유지된다.
//        // 즉, 기존에 처음 페이지에 들어갈 때는 userDTO가 parameter로 들어오지 않으니 무시되고,
//        // 회원가입 실패시, UserDTO를 받은 Get요청이 이루어지면서 model을 통해 넘어온 값이 parameter 로 받아지게 된다.
//
//
//    }
    @PostMapping("register")
    public String register(@Valid UserDTO userDTO, Errors errors,Model model) {

        /* post요청시 넘어온 user 입력값에서 Validation에 걸리는 경우 */
        if (errors.hasErrors()) {
            /* 회원가입 실패시 입력 데이터 유지 */
            model.addAttribute("userDTO", userDTO);
            /* 회원가입 실패시 message 값들을 모델에 매핑해서 View로 전달 */
            Map<String, String> validateMap = new HashMap<>();

            for (FieldError error : errors.getFieldErrors()) {
                String validKeyName = "valid_" + error.getField();
                validateMap.put(validKeyName, error.getDefaultMessage());
            }

            // map.keySet() -> 모든 key값을 갖고온다.
            // 그 갖고온 키로 반복문을 통해 키와 에러 메세지로 매핑
            for (String key : validateMap.keySet()) {
                model.addAttribute(key, validateMap.get(key));
                System.out.println(key);
                System.out.println(validateMap.get(key));
            }

            return "sign-up";
        }


         String rawPassword = userDTO.getUser_password();   //회원가입시 입력받은 비밀번호를 저장
         String encPassword = bCryptPasswordEncoder.encode(rawPassword); //저장한 비밀번호를 암호화
         userDTO.setUser_password(encPassword);
         userService.insertUser(userDTO);
         profileService.insertProfile(userDTO.getUser_id());
       return "redirect:/";
    }

    //로그인 폼 이동
    @GetMapping("/loginForm")
    public String login() {

        return "loginForm";
    }

    //로그인
    @PostMapping("/submit")
    public String submit (UserDTO dto) {

        return "main";
    }

    // OAuth 로그인을 해도 PrincipalDetails
    // 일반 로그인을 해도 PrincipalDetails
    @GetMapping("/user")
    public @ResponseBody String user (@AuthenticationPrincipal PrincipalDetails principalDetails,Authentication authentication) {
        System.out.println("principalDetails:" + principalDetails.getDto());
        System.out.println("authentication:" + authentication.getPrincipal());
        System.out.println(principalDetails.getUsername());
        return "user";
    }


    //이름변경
    @PostMapping("/user/nameUpdate")
    @ResponseBody
    public ResponseEntity nameUpdate(@RequestBody Map<String,String> name, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if(userService.selectUserName(name.get("name"))==null) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", name.get("name"));
            map.put("email", principalDetails.getDto().getUser_email());
            userService.updateName(map);
            principalDetails.getDto().setUser_name(name.get("name"));
            return ResponseEntity.status(HttpStatus.OK).body("");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    //이메일변경

    @PostMapping("/user/emailUpdate")
    @ResponseBody
    public ResponseEntity emailUpdate(@RequestBody Map<String,String> email, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if(userService.selectUserEmail(email.get("email"))==null) {
            Map<String, String> map = new HashMap<>();
            map.put("email1", email.get("email"));
            map.put("email2", principalDetails.getDto().getUser_email());
            userService.updateEmail(map);
            principalDetails.getDto().setUser_email(email.get("email"));

            return ResponseEntity.status(HttpStatus.OK).body("");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    //비밀번호 변경

    @PostMapping("/user/passwordUpdate")
    @ResponseBody
    public ResponseEntity passwordUpdate(@RequestBody Map<String,String> password, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if(userService.selectUserEmail(password.get("password"))==null) {
            Map<String, String> map = new HashMap<>();
            map.put("password", password.get("password"));
            map.put("email", principalDetails.getDto().getUser_email());
            userService.updatePassword(map);

            return ResponseEntity.status(HttpStatus.OK).body("");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

//    @PostMapping("/user/profile/nameUpdate")
//    public String nameUpdate(@AuthenticationPrincipal PrincipalDetails principalDetails,@RequestBody Map<String,String> name) {
//       Map<String,Object> map = new HashMap<>();
//       map.put("name",name.get("name"));
//       map.put("email",principalDetails.getDto().getUser_email());
//       map.put("userid",principalDetails.getDto().getUser_id());
//      userService.updateName(map);
//      profileService.updateName(map);
//
//        return null;
//    }

    @PostMapping("/user/profile/nameUpdate")
    public String nameUpdate(@AuthenticationPrincipal PrincipalDetails principalDetails,@RequestParam("nameInput") String name) {
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("email",principalDetails.getDto().getUser_email());
        map.put("userid",principalDetails.getDto().getUser_id());
        userService.updateName(map);
        profileService.updateName(map);
        principalDetails.getDto().setUser_name(name);

        return "redirect:/user/profile";
    }

    @PostMapping("/user/profile/serviceUpdate")
    public String serviceUpdate(@AuthenticationPrincipal PrincipalDetails principalDetails,@RequestParam("serviceInput") String service) {
        Map<String,Object> map = new HashMap<>();
        map.put("service",service);
        map.put("email",principalDetails.getDto().getUser_email());
        map.put("userid",principalDetails.getDto().getUser_id());

        profileService.updateService(map);


        return "redirect:/user/profile";
    }

    @PostMapping("/user/profile/introduceUpdate")
    public String introduceUpdate(@AuthenticationPrincipal PrincipalDetails principalDetails,@RequestParam("introduceInput") String introduce) {
        Map<String,Object> map = new HashMap<>();
        map.put("introduce",introduce);
        map.put("email",principalDetails.getDto().getUser_email());
        map.put("userid",principalDetails.getDto().getUser_id());

        profileService.updateIntroduce(map);


        return "redirect:/user/profile";
    }

    @PostMapping("/user/profile/addressUpdate")
    public String addressUpdate(@AuthenticationPrincipal PrincipalDetails principalDetails,@RequestParam("addressInput") String address) {
        Map<String,Object> map = new HashMap<>();
        map.put("address",address);
        map.put("email",principalDetails.getDto().getUser_email());
        map.put("userid",principalDetails.getDto().getUser_id());

        
        profileService.updateAddress(map);


        return "redirect:/user/profile";
    }

    @PostMapping("/user/profile/timeUpdate")
    public String timeUpdate(@AuthenticationPrincipal PrincipalDetails principalDetails,@RequestParam("timeRange") String time) {
        System.out.println(time);
        Map<String,Object> map = new HashMap<>();
        map.put("time",time);
        map.put("email",principalDetails.getDto().getUser_email());
        map.put("userid",principalDetails.getDto().getUser_id());


        profileService.updateTime(map);


        return "redirect:/user/profile";
    }

    @PostMapping("/user/profile/deployeeUpdate")
    public String deployeeUpdate(@AuthenticationPrincipal PrincipalDetails principalDetails,@RequestParam("deployee") String deployee) {

        Map<String,Object> map = new HashMap<>();
        map.put("deployee",deployee);
        map.put("email",principalDetails.getDto().getUser_email());
        map.put("userid",principalDetails.getDto().getUser_id());


        profileService.updateDeployee(map);


        return "redirect:/user/profile";
    }

    @PostMapping("/user/profile/detailUpdate")
    public String detailUpdate(@AuthenticationPrincipal PrincipalDetails principalDetails,@RequestParam("detail") String detail) {

        Map<String,Object> map = new HashMap<>();
        map.put("detail",detail);
        map.put("email",principalDetails.getDto().getUser_email());
        map.put("userid",principalDetails.getDto().getUser_id());


        profileService.updateDetail(map);


        return "redirect:/user/profile";
    }




    @GetMapping("/auth/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model) {

        /* 에러와 예외를 모델에 담아 view resolve */
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "error";
    }
}
