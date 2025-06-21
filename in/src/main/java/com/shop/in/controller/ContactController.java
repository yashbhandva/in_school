package com.shop.in.controller;

import com.shop.in.servicImpl.ContactServiceImpl;
import com.shop.in.service.ContactService;
import com.shop.in.model.Contact;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class ContactController {

    @Autowired
    private ContactServiceImpl service;

    @GetMapping("/contact")
    public String contact(Model model){
        model.addAttribute("contact" ,new Contact());
        return "contact";
    }

//    @PostMapping("/saveMsg")
//    public String saveMessage(
//            @RequestParam String name,
//            @RequestParam String mobileNum,
//            @RequestParam String email,
//            @RequestParam String subject,
//            @RequestParam String message){
//
//            log.info("name : "+name);
//            log.info("mobile number : "+mobileNum);
//            log.info("email : "+email);
//            log.info("subject : "+subject);
//            log.info("message : "+message);
//
//        return "redirect:contact";
//    }



    @PostMapping("/saveMsg")
    public String saveInfo(@Valid @ModelAttribute Contact contact, Errors error){
        if (error.hasErrors()){
            log.error("contact form validation fild dau to "+error);
            return "contact.html";
        }
        service.saveInfo(contact);
        return "redirect:/contact";
    }

    @GetMapping("/displayMessages/page/{pageNum}")
    public String displayMessages(Model model,
                                  @PathVariable(name = "pageNum") int pageNum,
                                  @RequestParam String sortField ,
                                  @RequestParam String sortDir){
        //List<Contact> list = service.getAllInfo();

        Page<Contact> page = service.findPaginated(pageNum,sortField,sortDir);
        List<Contact> contactMsgs = page.getContent();

        model.addAttribute("currentPage" ,pageNum);
        model.addAttribute("totalPages" ,page.getTotalPages());
        model.addAttribute("totalMsgs" ,page.getTotalElements());
        model.addAttribute("sortField" ,sortField);
        model.addAttribute("sortDir" ,sortDir);
        model.addAttribute("reverseSortDir" ,sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("contactMsgs" ,contactMsgs);

        return "messages.html";
    }

    @GetMapping("/closeMsg")
    public String deleteMessage(@RequestParam int id) {
        service.deleteContact(id);
        // Redirect with default pagination params
        return "redirect:/displayMessages/page/1?sortField=name&sortDir=asc";
    }

}
