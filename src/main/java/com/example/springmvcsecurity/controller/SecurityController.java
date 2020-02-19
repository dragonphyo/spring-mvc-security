package com.example.springmvcsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/")
    public String welcome(Model model){
        model.addAttribute("tagline","Hello Spring Security");
        return "user/home";
    }
    @GetMapping("/admin/delete")
    public String delete(Model model){
        model.addAttribute("delete","You are Admin and Delete Page.");
        return "admin/delete";
    }
    @GetMapping("/user/posts")
    public String showAllPosts(Model model){
        model.addAttribute("message","Show All Posts");
        return "user/posts";
    }
}
