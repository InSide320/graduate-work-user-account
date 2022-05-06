package com.example.controllers;

import com.example.user.UserPersonalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    UserPersonalService userPersonalService;

    public MainController(UserPersonalService userPersonalService) {
        this.userPersonalService = userPersonalService;
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("title", userPersonalService.getUserById(1));
        return "index";
    }

}
