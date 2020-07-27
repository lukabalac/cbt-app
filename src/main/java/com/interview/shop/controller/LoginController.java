package com.interview.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @GetMapping("/login")
    public ModelAndView getLoginPage(){
        var mav = new ModelAndView("login");
        return mav;
    }
    @GetMapping("/access-denied")
    public ModelAndView getEntryDeniedPage(){
        var mav = new ModelAndView("login");
        return mav;
    }
}
