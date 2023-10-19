package com.longlive.kmong.controller;

import com.longlive.kmong.DTO.UserDTO;
import com.longlive.kmong.config.auth.PrincipalDetails;
import com.longlive.kmong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class kmongController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String main (@AuthenticationPrincipal PrincipalDetails principalDetails , Model model,Authentication authentication) {

//            model.addAttribute("user", principalDetails.getDto());
//            principalDetails.getDto().getUser_name()

        return "main";
    }
    @GetMapping("/map")
    public String map (Model model) {
       List<UserDTO> dto = userService.getAddress();
    model.addAttribute("address" , dto);

        return "map";
    }

//    @GetMapping("/chat")
//    public String chat(@AuthenticationPrincipal PrincipalDetails principalDetails , Model model) {
//       model.addAttribute("user", principalDetails.getDto());
//        return"chat";
//    }
@GetMapping({"/chat", "/chat/{roomId}/{nickname}"})
public String myChat(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
    model.addAttribute("user", principalDetails.getDto());
    System.out.println(principalDetails.getDto().getUser_id());
    return "/chat";

}
//@GetMapping("/sms/findpw")
//    public String findpw (){
//        return "findpw";
//}


}
