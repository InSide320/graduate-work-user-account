package com.example.controllers.mvc;

import com.example.user.details.introductory.EstimatesEntity;
import com.example.user.details.introductory.EstimatesService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping({"/change-data", "/change"})
public class ChangeDataController {
    EstimatesService estimatesService;

    public ChangeDataController(EstimatesService estimatesService) {
        this.estimatesService = estimatesService;
    }

    @GetMapping
    public String print(Model model) {
        model.addAttribute("getForm");
        return "change-data";
    }

//    @GetMapping("{id}")
//    public String show(@PathVariable long id, Model model) {
//        model.addAttribute("entrance", estimatesService.getById(id));
//        return "change-data";
//    }

    @PatchMapping
    public String updateDataEntrance(
            Model model,
            @RequestParam Integer ukLang,
            @RequestParam Integer mathSubject,
            @RequestParam Integer additionalSubject, @RequestParam long id) {
        List<Integer> avgSubject = new ArrayList<>(List.of(ukLang, mathSubject, additionalSubject));
        model.addAttribute("updateData",
                estimatesService.updateEstimates(
                        ukLang, mathSubject, additionalSubject,
                        avgSubject.stream().mapToDouble(avg -> avg).average().getAsDouble(),
                        avgSubject.stream().mapToInt(Integer::intValue).max().getAsInt(),
                        avgSubject.stream().mapToInt(Integer::intValue).min().getAsInt(),
                        id)
        );
        return "redirect:personal-data";
    }
}
