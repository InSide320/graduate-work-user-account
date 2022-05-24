package com.example.controllers.mvc;

import com.example.user.UserPersonalService;
import com.example.user.credentials.CredentialUserEntity;
import com.example.user.credentials.CredentialsUserService;
import com.example.user.credentials.role.RoleType;
import com.example.user.details.introductory.EstimatesService;
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
    public String print(Model model) {
        model.addAttribute("getForm")
                .addAttribute("credentialData",
                        credentialsUserService.findAll());
        return "change-data";
    }

    @GetMapping("/role-type/{credentialId}")
    public String changeDataUserPersonalData(
            @PathVariable(value = "credentialId") CredentialUserEntity credentialUser,
            Model model) {
        model.addAttribute("credentialUser", credentialUser);
        return "editRoleType";
    }

    @PatchMapping("/role-type")
    public String updateRoleUser(
            @RequestParam(value = "userId") long id,
            @RequestParam(value = "role-type") RoleType roleType,
            Model model) {
        model.addAttribute("updateRole",
                credentialsUserService.updateRoleTypeUser(id, roleType)
        );
        return "redirect:/change-data";
    }

    @PatchMapping("/entrance")
    public String updateDataEntrance(
            Model model,
            @RequestParam Integer ukLang,
            @RequestParam Integer mathSubject,
            @RequestParam Integer additionalSubject,
            @RequestParam long id) {
        List<Integer> avgSubject = new ArrayList<>(
                List.of(ukLang,
                        mathSubject,
                        additionalSubject)
        );
        model.addAttribute("updateData",
                estimatesService.updateEstimates(
                        ukLang, mathSubject, additionalSubject,
                        avgSubject.stream().mapToDouble(avg -> avg).average().getAsDouble(),
                        avgSubject.stream().mapToInt(Integer::intValue).max().getAsInt(),
                        avgSubject.stream().mapToInt(Integer::intValue).min().getAsInt(),
                        id)
        );
        return "redirect:/personal-data";
    }
}
