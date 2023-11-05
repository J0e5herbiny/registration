package com.joe.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginHtmlController {


    @GetMapping("/loginAuthPage")
    public String loginPage(){
        return "login-authPage";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "access-denied";
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/managers")
    public String managers(){
        return "managers";
    }

    @GetMapping("/systems")
    public String systems(){
        return "systems";
    }

}
