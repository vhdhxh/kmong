package com.longlive.kmong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class kmongController {

    @GetMapping("/")
    public String main () {
        return "main";
    }

}
