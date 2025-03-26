package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

@Controller
public class PageController {

    @GetMapping("/home")
    public String home(Model model) {
        System.out.println("PageController: Passing through...");
        model.addAttribute("name", "kamalsokhal");
        model.addAttribute("message", "Welcome to SCM");
        model.addAttribute("title", "Home");
        model.addAttribute("date", new java.util.Date());
        return "home";
    }
    
    @GetMapping("/about")
    public String about(Model model) {
        System.out.println("PageController: Passing through...");
        model.addAttribute("title", "About");
        return "about";
    }
    @GetMapping("/services")
    public String services(Model model) {
        System.out.println("PageController: Passing through...");
        model.addAttribute("title", "Services");
        return "services";
    }
    @GetMapping("/contact") 
    public String contact(Model model) {
        System.out.println("PageController: Passing through...");
        model.addAttribute("title", "Contact");
        return "contact";
    }
    @GetMapping("/register")  
    public String register(Model model) {
        System.out.println("PageController: Passing through...");
        model.addAttribute("title", "Register");
        return "register";
    }
    @GetMapping("/login") 
    public String login(Model model) {
        System.out.println("PageController: Passing through...");
        model.addAttribute("title", "Login");
        return "login";
    }    


}
