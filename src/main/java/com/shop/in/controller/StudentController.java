package com.shop.in.controller;

import com.shop.in.model.Person;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/displayCourses")
    public String displayCourses(Model model , HttpSession session){
        Person person = (Person) session.getAttribute("loggedInPerson");
        model.addAttribute("person" ,person);
        return "courses_enrolled.html";
    }
}
