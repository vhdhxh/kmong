package com.longlive.kmong.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

//@Controller
//public class WebErrorController implements ErrorController {
//
//
//
//    @GetMapping("/error")
//    public String handleError(HttpServletRequest request) {
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//
//        if(status != null){
//            int statusCode = Integer.valueOf(status.toString());
//
//            if(statusCode == HttpStatus.NOT_FOUND.value()) {
//                System.out.println("error123");
//                return "error/error123";
//
//            } else {
//                return "error/error";
//            }
//        }
//
//        return "error/error";
//    }

//}