package com.example.controllers.mvc;

import com.example.user.UserPersonalDataEntity;
import com.example.user.UserPersonalService;
import com.example.user.details.introductory.EstimatesService;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.logging.Level;
import java.util.stream.Stream;

@Controller
@RequestMapping({"/manipulation-data", "/manipulation"})
public class ManipulationDataController {
    UserPersonalService userPersonalService;
    EstimatesService estimatesService;
    java.util.logging.Logger logger = java.util.logging.Logger.getGlobal();
    Logger loggerFactory = LoggerFactory.getLogger(ManipulationDataController.class);

    public ManipulationDataController(
            UserPersonalService userPersonalService,
            EstimatesService estimatesService) {
        this.userPersonalService = userPersonalService;
        this.estimatesService = estimatesService;
    }

    @GetMapping
    public String print(Model model) {
        model.addAttribute("getFormManipulation", new UserPersonalDataEntity());
        return "manipulation";
    }

    @PostMapping
    public String getInformationByGroup(@RequestParam("group") String group, Model model) {
        model.addAttribute("getGroupData",
                userPersonalService.getInformationByGroup(group));
        return "manipulation";
    }
}
