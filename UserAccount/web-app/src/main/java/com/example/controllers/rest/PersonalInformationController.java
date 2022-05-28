package com.example.controllers.rest;

import com.example.user.UserPersonalDataEntity;
import com.example.user.UserPersonalService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web-api/users")
@Api
public class PersonalInformationController {
    UserPersonalService userPersonalService;

    public PersonalInformationController(UserPersonalService userPersonalService) {
        this.userPersonalService = userPersonalService;
    }

    @GetMapping("{userId}")
    public UserPersonalDataEntity getUserById(@PathVariable("userId") long userId) {
        return userPersonalService.getUserById(userId);
    }

    @GetMapping
    public List<UserPersonalDataEntity> findAllUser() {
        return userPersonalService.findAll();
    }

    @PostMapping
    public UserPersonalDataEntity saveUser(@RequestBody UserPersonalDataEntity userPersonalData) {
        return userPersonalService.save(userPersonalData);
    }

    @DeleteMapping("{userId}")
    public boolean deleteUserById(@PathVariable Long userId) {
        return userPersonalService.deleteById(userId);
    }
}
