package com.example.user.credentials.generate;

import com.example.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

@Component
public class GenerateCredentialsEmail {

    private static final Logger logger = LoggerFactory.getLogger(GenerateCredentialsEmail.class);

    private GenerateCredentialsEmail() {
    }

    public static String generateEmail(String lastName, String firstName) {
        if (lastName.isBlank()) {
            throw new ValidationException("lastName is blank");
        }
        if (firstName.isBlank()) {
            throw new ValidationException("first name is blank");
        }

        Random random = null;
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage(), e);
        }

        return (lastName + "_"
                + firstName.charAt(0)
                + Objects.requireNonNull(random).nextInt(1, 9)
                + "@chpt.edu.ua").toLowerCase(Locale.ROOT);
    }

}
