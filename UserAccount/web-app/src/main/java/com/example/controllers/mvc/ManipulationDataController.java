package com.example.controllers.mvc;

import com.example.user.UserPersonalDataEntity;
import com.example.user.UserPersonalService;
import com.example.user.credentials.CredentialUserEntity;
import com.example.user.details.introductory.EstimatesService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/manipulation-data", "/manipulation"})
public class ManipulationDataController {
    UserPersonalService userPersonalService;
    EstimatesService estimatesService;

    public ManipulationDataController(
            UserPersonalService userPersonalService,
            EstimatesService estimatesService) {
        this.userPersonalService = userPersonalService;
        this.estimatesService = estimatesService;
    }

    @GetMapping
    public String print(Model model,
                        @AuthenticationPrincipal CredentialUserEntity credentialUserEntity) {
        model.addAttribute("getFormManipulation", new UserPersonalDataEntity())
                .addAttribute("getAuthorizedUser",
                        credentialUserEntity.getAuthEmail());
        return "manipulation";
    }

    @PostMapping
    public String getInformationByGroup(@RequestParam("group") String group, Model model) {
        model.addAttribute("getGroupData",
                userPersonalService.getInformationByGroup(group));
        return "manipulation";
    }
}
