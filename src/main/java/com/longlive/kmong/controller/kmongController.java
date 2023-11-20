package com.longlive.kmong.controller;

import com.longlive.kmong.DAO.Profile;
import com.longlive.kmong.DTO.*;
import com.longlive.kmong.config.auth.PrincipalDetails;
import com.longlive.kmong.service.BoardService;
import com.longlive.kmong.service.ProfileService;
import com.longlive.kmong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@RequiredArgsConstructor
@Controller
@PropertySource("classpath:config.properties")
public class kmongController {

   private final ProfileService profileService;
   private final UserService userService;
    private final BoardService boardService;

    @Value("s3.region")
    String a;
    @GetMapping("/")
    public String main (@ModelAttribute("params")  SearchDto params,Model model) {
        System.out.println(boardService.findAllPost(params).getPagination());
        System.out.println(boardService.findAllPost(params).getList());
        PagingResponse<BoardListDto> response = boardService.findAllPost(params);
        model.addAttribute("response", response);
        System.out.println(response);
        System.out.println(a);
//            model.addAttribute("user", principalDetails.getDto());
//            principalDetails.getDto().getUser_name()
//        if (principalDetails != null && principalDetails.getDto() != null) {
//            model.addAttribute("image", principalDetails.getDto().getUser_image());
//        }


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
    return "chat";

}
@GetMapping("/user/account-update")
public String accountupdate() {
        return "userupdate";
    }

@GetMapping("/user/profile")
public String profile(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
    ProfileDTO profileDTO = profileService.selectProfile(principalDetails.getDto().getUser_id());
        model.addAttribute("profile",profileDTO);
        return "userprofile";
    }

@GetMapping("/board/write")
public String write() {
        return "write";
}

@GetMapping("/board/{board_id}")
public String detail(@PathVariable String board_id) {

 return "boarddetail";
    }

    @GetMapping("/board/update/{board_id}")
    public String update(@PathVariable String board_id, Model model) {

        model.addAttribute("board",boardService.findPost(board_id));
        return "update";
    }

    @GetMapping("/order/{id}")
    public String order(@ModelAttribute("params") ItemDto itemDto) {

        return "payments";
    }
    @GetMapping("/user/order")
    public String getOrder() {
        return "order";
    }

    @GetMapping("/profile/{userid}")
    public String profile() {
        return "profile";
    }

    @GetMapping("/user/myBoards")
    public String myBoards() {return "myBoards";}

    @GetMapping("/user/myComments")
    public String myComments() {return "myComments";}

    @GetMapping("/user/mySales")
    public String mySales() {return "mySales";}

@GetMapping("/uploadtest")
    public String uploadtest() {
        return "uploadtest";
}
//@GetMapping("/sms/findpw")
//    public String findpw (){
//        return "findpw";
//}


}
