package com.shop.in.controller;

import com.shop.in.model.Address;
import com.shop.in.model.Person;
import com.shop.in.model.Profile;
import com.shop.in.repository.AddressRepo;
import com.shop.in.repository.PersonRepo;
import com.shop.in.servicImpl.PersonServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller("profileControllerBean")
public class ProfileController {

    @Autowired
    private PersonServiceImpl service;

    @Autowired
    private PersonRepo repo;

    @Autowired
    private AddressRepo addressRepo;

    @RequestMapping("/displayProfile")
    public String displayMessage(Model model , HttpSession session){
        Person person = (Person) session.getAttribute("loggedInPerson");

        Profile profile = new Profile();
        profile.setName(person.getName());
        profile.setEmail(person.getEmail());
        profile.setMobileNumber(person.getMobileNumber());

        if (person.getAddress()!=null && person.getAddress().getId()>0){
            profile.setAddress1(person.getAddress().getAddress1());
            profile.setAddress1(person.getAddress().getAddress2());
            profile.setAddress1(person.getAddress().getCity());
            profile.setAddress1(person.getAddress().getState());
            profile.setAddress1(person.getAddress().getZipCode());
        }
        model.addAttribute("profile" ,profile);
        return "profile.html";
    }

    @RequestMapping("/updateProfile")
    public String updatePersonProfile(@Valid @ModelAttribute("profile") Profile profile , Errors errors ,HttpSession session){
        Person person = (Person)session.getAttribute("loggedInPerson");

        if (errors.hasErrors()){
            return "profile.html";
        }
        person.setName(profile.getName());
        person.setEmail(profile.getEmail());
        person.setMobileNumber(profile.getMobileNumber());

        if (person.getAddress()==null || !(person.getAddress().getId()>0)){
            person.setAddress(new Address());
        }

        person.getAddress().setAddress1(profile.getAddress1());
        person.getAddress().setAddress2(profile.getAddress2());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZipCode(profile.getZipCode());

        repo.save(person);
        session.setAttribute("loggedInPerson",person);



        return "redirect:/displayProfile";
    }

}
