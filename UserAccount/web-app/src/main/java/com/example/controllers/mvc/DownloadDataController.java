package com.example.controllers.mvc;

import com.example.exception.MessageException;
import com.example.sender.MailSenderService;
import com.example.user.UserPersonalDataEntity;
import com.example.user.UserPersonalService;
import com.example.user.credentials.CredentialUserEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/download")
public class DownloadDataController {
    UserPersonalService userPersonalService;
    MailSenderService senderService;

    public DownloadDataController(UserPersonalService userPersonalService, MailSenderService senderService) {
        this.userPersonalService = userPersonalService;
        this.senderService = senderService;
    }

    @GetMapping
    public String print(Model model,
                        @AuthenticationPrincipal CredentialUserEntity credentialUserEntity) {
        model.addAttribute("getForm")
                .addAttribute("getAuthorizedUser",
                        userPersonalService.getUserById(credentialUserEntity.getId()));
        return "/download";
    }

    @PostMapping
    public String print(
            @AuthenticationPrincipal CredentialUserEntity credentialUserEntity)
            throws MessageException, IOException {
        File file = new File(UUID.randomUUID() + "_personalInformation.txt");
        if (file.createNewFile() || file.exists()) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                List<UserPersonalDataEntity> userPersonalDataEntities
                        = new ArrayList<>(userPersonalService.findAll());
                for (UserPersonalDataEntity users : userPersonalDataEntities) {
                    bufferedWriter.write("---------------------------------------\n");
                    bufferedWriter.write(
                            "Full name: " + users.getFirstNameTransliteration()
                                    + " " + users.getLastNameTransliteration());
                    bufferedWriter.write("\n");
                    bufferedWriter.write("Full name by cyrillic: "
                            + users.getFirstNameCyrillic()
                            + " " + users.getLastNameCyrillic()
                            + " " + users.getMidlNameCyrillic() + "\n");
                    bufferedWriter.write(
                            users.getDetailUserEntity().getEstimatesEntity().toString() + "\n");
                    bufferedWriter.write(users.getCredentialUserEntity().toString());
                    bufferedWriter.write("\n---------------------------------------\n");
                }
            }
        }

        senderService.sendFile(credentialUserEntity.getBackupEmail(),
                "Users credentials",
                "File with extorted users credentials", file.getAbsolutePath());

        Files.delete(Path.of(file.getPath()));

        return "redirect:/manipulation";
    }
}
