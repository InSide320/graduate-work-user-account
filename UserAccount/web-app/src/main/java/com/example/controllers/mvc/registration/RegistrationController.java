package com.example.controllers.mvc.registration;

import com.example.user.UserPersonalDataEntity;
import com.example.user.UserPersonalService;
import com.example.user.credentials.CredentialUserEntity;
import com.example.user.credentials.CredentialsUserService;
import com.example.user.credentials.generate.GenerateCredentialsEmail;
import com.example.user.credentials.generate.GenerateCredentialsPassword;
import com.example.user.credentials.role.RoleType;
import com.example.user.details.DetailUserEntity;
import com.example.user.details.DetailsUserService;
import com.example.user.details.backup.BackupUserDataService;
import com.example.user.details.introductory.EstimatesEntity;
import com.example.user.details.introductory.EstimatesService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public record RegistrationController(
        UserPersonalService userPersonalService,
        DetailsUserService detailsUserService,
        BackupUserDataService backupUserDataService,
        CredentialsUserService credentialsUserService,
        EstimatesService estimatesService,
        PasswordEncoder encoder
) {

    @GetMapping("/registration")
    public String viewUserPersonalData(Model model) {
        UserPersonalDataEntity userPersonalDataEntity = new UserPersonalDataEntity();
        model.addAttribute("registrationForm", userPersonalDataEntity)
                .addAttribute("giveCredentialToUser", new ArrayList<String>());
        return "/registration";
    }

    @GetMapping("/activate/{code}")
    public String activateEmail(Model model, @PathVariable String code) {
        boolean isActivated = credentialsUserService.activateUser(code);
        if (isActivated) {
            model.addAttribute("messages", "User successfully activated");
        } else {
            model.addAttribute("messages", "Activation code is not found!");
        }
        return "/login";
    }

    @PostMapping("/registration")
    public String saveUser(
            @ModelAttribute("registrationForm")
            @Validated UserPersonalDataEntity userPersonalData) {
        CredentialUserEntity credentialUserEntity = new CredentialUserEntity(
                GenerateCredentialsEmail.generateEmail(
                        userPersonalData.getLastNameTransliteration(),
                        userPersonalData.getFirstNameTransliteration()),
                GenerateCredentialsPassword.generateStrongPassword(),
                RoleType.STUDENT,
                userPersonalData.getCredentialUserEntity().getBackupEmail()
        );
        userPersonalService.saveAndFlush(
                new UserPersonalDataEntity(
                        userPersonalData.getId(), userPersonalData.getLastNameTransliteration(),
                        userPersonalData.getFirstNameTransliteration(),
                        userPersonalData.getLastNameCyrillic(),
                        userPersonalData.getFirstNameCyrillic(),
                        userPersonalData.getMidlNameCyrillic(),
                        detailsUserService.saveAndFlush(
                                new DetailUserEntity(
                                        userPersonalData.getDetailUserEntity().getDateEnter(),
                                        userPersonalData.getDetailUserEntity().getDateRelease(),
                                        userPersonalData.getDetailUserEntity().getGroupCyrillic(),
                                        userPersonalData.getDetailUserEntity()
                                                .getGroupTransliteration(),
                                        backupUserDataService.saveAndFlush(
                                                userPersonalData.getDetailUserEntity()
                                                        .getBackupUserDataEntity()),
                                        estimatesService.saveAndFlush(new EstimatesEntity())
                                )),
                        credentialsUserService.saveAndFlush(credentialUserEntity)
                )
        );
        return "redirect:/login";
    }
}
