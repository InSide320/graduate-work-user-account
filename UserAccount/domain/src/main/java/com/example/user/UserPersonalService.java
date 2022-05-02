package com.example.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public record UserPersonalService(List<UserPersonalData> userPersonalDataList) {
    private static UserPersonalService instance;

    public static UserPersonalService getInstance() {
        if (instance == null) {
            instance = new UserPersonalService(new ArrayList<>());
        }
        return instance;
    }

    private static final Logger logger = Logger.getGlobal();

    public Boolean addNewUserByList(UserPersonalData userPersonalData) {
        return userPersonalDataList().add(userPersonalData);
    }

    private void getIdUser() {
        for (UserPersonalData userPersonalData : userPersonalDataList()) {
            String lineIdUsers = userPersonalData.getId()
                    + ") "
                    + userPersonalData.getLastNameTransliteration();

            logger.log(Level.INFO, lineIdUsers);
        }
    }

    public UserPersonalData getUserById(int id) throws IllegalArgumentException {
        getIdUser();
        if (id <= userPersonalDataList().size() && id > 0)
            return userPersonalDataList().get(id);
        else
            throw new IllegalArgumentException("id cannot be larger");
    }


}
