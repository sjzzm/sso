package com.ithuang.ekp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class EkpController {

    @GetMapping("/ekp")
    public String login(@RequestParam(value = "token",required = false) String token, HttpSession session){
        if(!StringUtils.isEmpty(token)){
            session.setAttribute("username","admin");
        }
        return "ekp";
    }
}
