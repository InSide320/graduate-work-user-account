package com.example.controllers.mvc;

import com.example.user.UserPersonalService;
import com.example.user.credentials.CredentialUserEntity;
import com.example.user.credentials.CredentialsUserService;
import com.example.user.credentials.role.RoleType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminPanelController {
    UserPersonalService userPersonalService;
    CredentialsUserService credentialsUserService;

    public AdminPanelController(UserPersonalService userPersonalService, CredentialsUserService credentialsUserService) {
        this.userPersonalService = userPersonalService;
        this.credentialsUserService = credentialsUserService;
    }

    @GetMapping
    public String print(Model model,
                        @AuthenticationPrincipal CredentialUserEntity credentialUserEntity) {
        model.addAttribute("getForm")
                .addAttribute("getAuthorizedUser", credentialUserEntity)
                .addAttribute("credentialData",
                        credentialsUserService.findAllWithSortingAsc());
        return "admin";
    }


    @GetMapping("/role-type/{credentialId}")
    public String changeDataUserPersonalData(
            @PathVariable(value = "credentialId") CredentialUserEntity credentialUser,
            Model model,
            @AuthenticationPrincipal CredentialUserEntity credentialUserEntity) {
        model.addAttribute("credentialUser", credentialUser)
                .addAttribute("getAuthorizedUser",
                        credentialUserEntity);
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
        return "redirect:/admin";
    }
}
