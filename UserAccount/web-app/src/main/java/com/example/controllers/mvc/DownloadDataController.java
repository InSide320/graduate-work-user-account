package com.example.controllers.mvc;

import com.example.user.UserPersonalDataEntity;
import com.example.user.UserPersonalService;
import com.example.user.credentials.CredentialUserEntity;
import com.example.user.credentials.CredentialsUserService;
import com.example.user.details.backup.BackupUserDataService;
import io.swagger.annotations.Authorization;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        return "/download";
    }

    @PostMapping
    public String print(
            @AuthenticationPrincipal CredentialUserEntity credentialUserEntity) throws IOException {

        File file = new File(UUID.randomUUID() + "_personalInformation.txt");
        if (file.createNewFile()) {
            if (file.exists()) {
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                    List<UserPersonalDataEntity> userPersonalDataEntities = new ArrayList<>(userPersonalService.findAll());
                    for (UserPersonalDataEntity users : userPersonalDataEntities) {
                        bufferedWriter.write("---------------------------------------");
                        bufferedWriter.write(
                                "Full name: " + users.getFirstNameTransliteration()
                                        + " " + users.getLastNameTransliteration());
                        bufferedWriter.write("\n");
                        bufferedWriter.write("Full name by cyrillic: "
                                + users.getFirstNameCyrillic()
                                + " " + users.getLastNameCyrillic()
                                + " " + users.getMidlNameCyrillic());
                        bufferedWriter.write("\n");
                        bufferedWriter.write(
                                users.getDetailUserEntity().getEstimatesEntity().toString()
                        );
                        bufferedWriter.write("\n");
                        bufferedWriter.write(users.getCredentialUserEntity().toString());
                        bufferedWriter.write("---------------------------------------\n");
                    }
                }
            }
        }

        return "redirect:/manipulation";
    }
}
