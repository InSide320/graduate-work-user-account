package com.example.controllers.mvc;

import com.example.user.UserPersonalService;
import com.example.user.credentials.CredentialUserEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/upload")
public class UploadDataController {

    UserPersonalService userPersonalService;

    public UploadDataController(UserPersonalService userPersonalService) {
        this.userPersonalService = userPersonalService;
    }

    @GetMapping
    public String print(Model model, @AuthenticationPrincipal CredentialUserEntity credentialUserEntity) {
        model.addAttribute("getForm")
                .addAttribute("getAuthorizedUser",
                        userPersonalService.getUserById(credentialUserEntity.getId()));
        return "upload";
    }
}
