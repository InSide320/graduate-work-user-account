package com.example.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public record UserPersonalService(UserPersonalDataRepository userPersonalDataRepository) {

    private static final Logger logger = Logger.getGlobal();

    public UserPersonalData addNewUserByList(UserPersonalData userPersonalData) {
        return userPersonalDataRepository.save(userPersonalData);
    }

    public List<UserPersonalData> findAll() {
        return userPersonalDataRepository.findAll();
    }


    public UserPersonalData getUserById(long id) throws IllegalArgumentException {
        if (id <= userPersonalDataRepository.findAll().size() && id > 0)
            return userPersonalDataRepository.getById(id);
        else
            throw new IllegalArgumentException("id cannot be larger");
    }


}
