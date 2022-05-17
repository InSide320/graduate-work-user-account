package com.example.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/download")
public class DownloadDataController {
    @GetMapping
    public String print(Model model) {
        model.addAttribute("getForm");
        return "download";
    }
}
