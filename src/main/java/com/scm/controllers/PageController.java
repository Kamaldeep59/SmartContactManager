package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.services.UserService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Controller
public class PageController {

    @Autowired
    private UserService userService;

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
    public String about() {
        System.out.println("PageController: Passing through...");
        return "about";
    }
    @GetMapping("/services")
    public String services() {
        System.out.println("PageController: Passing through...");
       
        return "services";
    }
    @GetMapping("/contact") 
    public String contact() {
        System.out.println("PageController: Passing through...");
        
        return "contact";
    }
    @GetMapping("/register")  
    public String register(Model model) {
        System.out.println("PageController: Passing through...");
        UserForm userForm = new UserForm();
        //userForm.setName("kamal");  kuch bhi hard coded krna h to
        model.addAttribute("userForm", userForm);
        return "register";
    }
    @GetMapping("/login") 
    public String login() {
        System.out.println("PageController: Passing through...");
        
        return "login";
    }    

    //processing register
    @RequestMapping(value = "/do-register",method =RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm) {
        System.out.println("processing register");
        //fetch data from userform
        System.out.println(userForm);
        //validate form

        //sae to database  
        //userservice

        User user = User.builder()
        .name(userForm.getName())
        .email(userForm.getEmail())
        .password(userForm.getPassword())
        .about(userForm.getAbout())
        .phoneNumber(userForm.getPhoneNumber())
        .profilePic("https://www.google.com/search?q=k+pic+download&oq=k+pic+download&gs_lcrp=EgZjaHJvbWUyCQgAEEUYORiABDIHCAEQABiABDIHCAIQABiABDIHCAMQABiABDIHCAQQABiABDIHCAUQABiABDIHCAYQABiABDIHCAcQABiABDIHCAgQABiABDIHCAkQABiABNIBCDI3MjBqMGo3qAIAsAIA&sourceid=chrome&ie=UTF-8#vhid=GwU_erjVaQn0oM&vssid=__djrZ8yHHNOw4-EP5_WfsQY_53")
        
        .build();

        User savedUser = userService.saveUser(user);
        System.out.println("User saved: " + savedUser);


       
        return "redirect:/register"; 
    }


}
