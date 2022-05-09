package com.example.controllers;

import com.example.user.UserPersonalDataEntity;
import com.example.user.UserPersonalService;
import com.example.user.credentials.CredentialsUserService;
import com.example.user.details.DetailsUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    UserPersonalService userPersonalService;
    DetailsUserService detailsUserService;
    CredentialsUserService credentialsUserService;

    public MainController(
            UserPersonalService userPersonalService,
            DetailsUserService detailsUserService,
            CredentialsUserService credentialsUserService) {
        this.userPersonalService = userPersonalService;
        this.detailsUserService = detailsUserService;
        this.credentialsUserService = credentialsUserService;
    }

    @GetMapping
    public String home(Model model) {

        Iterable<UserPersonalDataEntity> findAll = userPersonalService.findAll();

        model.addAttribute("title",
                findAll);
        return "index";
    }
}
