package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("PageController: Passing through...");
        model.addAttribute("name", "kamalsokhal");
        model.addAttribute("message", "Welcome to SCM");
        model.addAttribute("title", "Home");
        model.addAttribute("date", new java.util.Date());
        return "home";
    }

}
