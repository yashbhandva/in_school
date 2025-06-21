package com.shop.in.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetController {

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/courses")
    public String courses(){
        return "courses";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

}
