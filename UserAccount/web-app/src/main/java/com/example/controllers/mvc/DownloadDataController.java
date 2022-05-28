package com.example.controllers.mvc;

import com.example.user.UserPersonalService;
import com.example.user.credentials.CredentialUserEntity;
import com.example.user.credentials.CredentialsUserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/download")
public class DownloadDataController {
    UserPersonalService userPersonalService;

    public DownloadDataController(UserPersonalService userPersonalService) {
        this.userPersonalService = userPersonalService;
    }

    @GetMapping
    public String print(Model model,
                        @AuthenticationPrincipal CredentialUserEntity credentialUserEntity) {
        model.addAttribute("getForm")
                .addAttribute("getAuthorizedUser",
                        userPersonalService.getUserById(credentialUserEntity.getId()));
        return "download";
    }
}
