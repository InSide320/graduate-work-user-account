package com.example.controllers.mvc;

import com.example.exception.NotFoundException;
import com.example.user.UserPersonalDataEntity;
import com.example.user.UserPersonalService;
import com.example.user.credentials.CredentialUserEntity;
import com.example.user.credentials.CredentialsUserService;
import com.example.user.credentials.role.RoleType;
import com.example.user.details.introductory.EstimatesService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping({"/change-data", "/change"})
public class ChangeDataController {
    EstimatesService estimatesService;
    UserPersonalService userPersonalService;
    CredentialsUserService credentialsUserService;

    public ChangeDataController(
            EstimatesService estimatesService,
            UserPersonalService userPersonalService,
            CredentialsUserService credentialsUserService
    ) {
        this.estimatesService = estimatesService;
        this.userPersonalService = userPersonalService;
        this.credentialsUserService = credentialsUserService;
    }

    @GetMapping
    public String print(Model model,
                        @AuthenticationPrincipal CredentialUserEntity credentialUserEntity) {
        model.addAttribute("getForm")
                .addAttribute("personalData", userPersonalService.findAll())
                .addAttribute("getAuthorizedUser",
                        userPersonalService.getUserById(credentialUserEntity.getId()));
        return "change-data";
    }

    @GetMapping("/entrance/{userId}")
    public String getEntranceUser(
            @PathVariable(value = "userId") UserPersonalDataEntity userPersonalData,
            Model model,
            @AuthenticationPrincipal CredentialUserEntity credentialUserEntity) {
        if (userPersonalData == null) {
            throw new NotFoundException("Not found by Id");
        }
        model.addAttribute("personalData", userPersonalData)
                .addAttribute("getAuthorizedUser",
                        userPersonalService.getUserById(credentialUserEntity.getId()));
        return "editEntranceStudent";
    }

    @PatchMapping("/entrance")
    public String updateDataEntrance(
            @RequestParam Integer ukLang,
            @RequestParam Integer mathSubject,
            @RequestParam Integer additionalSubject,
            @RequestParam(value = "id") long id) {

        estimatesService.updateEstimates(ukLang,
                mathSubject,
                additionalSubject,
                id);
        return "redirect:/change-data";
    }
}
