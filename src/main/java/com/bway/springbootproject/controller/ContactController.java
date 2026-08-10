package com.bway.springbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bway.springbootproject.model.Contact;
import com.bway.springbootproject.utils.EmailUtils;

@Controller
public class ContactController {

    @Autowired
    private EmailUtils emailUtils;

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @PostMapping("/contact")
    public String postContact(@ModelAttribute Contact contact) {

        emailUtils.sendContactEmail(contact);

        return "redirect:/contact";
    }
}