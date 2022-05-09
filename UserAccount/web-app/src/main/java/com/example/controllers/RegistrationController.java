package com.example.controllers;

import com.example.user.UserPersonalDataEntity;
import com.example.user.UserPersonalService;
import com.example.user.credentials.CredentialUserEntity;
import com.example.user.credentials.CredentialsUserService;
import com.example.user.credentials.generate.GenerateCredentialsEmail;
import com.example.user.credentials.generate.GenerateCredentialsPassword;
import com.example.user.details.DetailUserEntity;
import com.example.user.details.DetailsUserService;
import com.example.user.details.backup.BackupUserDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public record RegistrationController(
        UserPersonalService userPersonalService,
        DetailsUserService detailsUserService,
        BackupUserDataService backupUserDataService,
        CredentialsUserService credentialsUserService
) {
    @GetMapping
    public String viewUserPersonalData(Model model) {
        UserPersonalDataEntity userPersonalDataEntity = new UserPersonalDataEntity();
        model.addAttribute("registrationForm", userPersonalDataEntity);
        return "registration";
    }

    @PostMapping
    public String saveUser(
            @ModelAttribute("registrationForm")
            @Validated UserPersonalDataEntity userPersonalData) {
        userPersonalService.saveAndFlush(
                new UserPersonalDataEntity(
                        userPersonalData.getId(), userPersonalData.getLastNameTransliteration(),
                        userPersonalData.getFirstNameTransliteration(),
                        userPersonalData.getLastNameCyrillic(),
                        userPersonalData.getFirstNameCyrillic(),
                        userPersonalData.getMidlNameCyrillic(),
                        detailsUserService.save(
                                new DetailUserEntity(
                                        userPersonalData.getDetailUserEntity().getRoleType(),
                                        userPersonalData.getDetailUserEntity().getDateEnter(),
                                        userPersonalData.getDetailUserEntity().getDateRelease(),
                                        userPersonalData.getDetailUserEntity().getGroupCyrillic(),
                                        userPersonalData.getDetailUserEntity()
                                                .getGroupTransliteration(),
                                        backupUserDataService.save(
                                                userPersonalData.getDetailUserEntity()
                                                        .getBackupUserDataEntity()
                                        )
                                )
                        ),
                        credentialsUserService.save(
                                new CredentialUserEntity(
                                        GenerateCredentialsEmail.generateEmail(
                                                userPersonalData.getLastNameTransliteration(),
                                                userPersonalData.getFirstNameTransliteration()),
                                        GenerateCredentialsPassword.generateStrongPassword()
                                )
                        )
                )
        );
        return "registration";
    }
}
