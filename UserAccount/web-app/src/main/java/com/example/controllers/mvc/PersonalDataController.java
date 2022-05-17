package com.example.controllers.mvc;

import com.example.controllers.PersonalInformationController;
import com.example.user.UserPersonalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/", "/personal-data"})
public class PersonalDataController {
    UserPersonalService userPersonalService;

    PersonalInformationController personalInformationController;

    public PersonalDataController(
            UserPersonalService userPersonalService,
            PersonalInformationController personalInformationController) {
        this.userPersonalService = userPersonalService;
        this.personalInformationController = personalInformationController;
    }

    @GetMapping
    public String print(Model model) {
        model.addAttribute("getForm");
        return "personal-data";
    }

    @PostMapping
    public String loginMenu(@RequestParam("userId") long userId, Model model) {
        model.addAttribute("info",
                userPersonalService.getUserById(userId));
        return "personal-data";
    }
}
