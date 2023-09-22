package com.longlive.kmong.controller;

import com.longlive.kmong.DTO.UserDTO;
import com.longlive.kmong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class kmongController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String main () {
        return "main";
    }
    @GetMapping("/map")
    public String map (Model model) {
       List<UserDTO> dto = userService.getAddress();
    model.addAttribute("address" , dto);

        return "map";
    }



}
