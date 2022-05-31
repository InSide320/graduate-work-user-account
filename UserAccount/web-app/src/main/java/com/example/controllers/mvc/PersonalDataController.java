package com.example.controllers.mvc;

import com.example.user.UserPersonalService;
import com.example.user.credentials.CredentialUserEntity;
import com.example.user.credentials.CredentialsUserService;
import com.example.user.details.DetailsUserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping({"/", "/personal-data"})
public class PersonalDataController {
    UserPersonalService userPersonalService;
    CredentialsUserService credentialsUserService;
    DetailsUserService detailsUserService;
    PasswordEncoder encoder;

    public PersonalDataController(
            UserPersonalService userPersonalService,
            CredentialsUserService credentialsUserService,
            PasswordEncoder encoder,
            DetailsUserService detailsUserService) {
        this.userPersonalService = userPersonalService;
        this.credentialsUserService = credentialsUserService;
        this.encoder = encoder;
        this.detailsUserService = detailsUserService;
    }

    @GetMapping
    public String personalInformationAboutTheUser(
            Model model,
            @AuthenticationPrincipal CredentialUserEntity credentialUserEntity) {
        model.addAttribute("getAuthorizedUser",
                userPersonalService.getUserById(credentialUserEntity.getId()));
        return "personal-data";
    }

    @GetMapping("/change-date")
    public String getFormChangeDate(
            @AuthenticationPrincipal CredentialUserEntity credentialUser,
            Model model) {
        model.addAttribute("getCredentialUser",
                userPersonalService.getUserById(credentialUser.getId()));

        return "editDate";
    }

    @PatchMapping("/change-date")
    public String patchDate(
            @AuthenticationPrincipal CredentialUserEntity credentialUserEntity,
            @RequestParam(value = "enter")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enter,
            @RequestParam(value = "release")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate release) {
        detailsUserService.updatesDatesIntroducedAndReleased(
                userPersonalService.getUserById(credentialUserEntity.getId()).getDetailUserEntity(),
                LocalDate.of(enter.getYear(), enter.getMonth(), enter.getDayOfMonth()),
                LocalDate.of(release.getYear(), release.getMonth(), release.getDayOfMonth())
        );
        return "redirect:/personal-data";
    }


    @GetMapping("/change-password")
    public String getFormChangePassword(
            @AuthenticationPrincipal CredentialUserEntity credentialUser,
            Model model
    ) {
        model.addAttribute("getCredentialUser", credentialUser);
        return "changePassword";
    }

    @PatchMapping("/change-password")
    public String changePassword(
            Model model,
            @AuthenticationPrincipal CredentialUserEntity credentialUser,
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            @RequestParam String repeatedPassword) {
        credentialsUserService.changePasswordUser(credentialUser, oldPassword, newPassword, repeatedPassword);
        return "redirect:/personal-data";
    }

}
