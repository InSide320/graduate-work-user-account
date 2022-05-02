package com.example.user.credentials.generate;

import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

@Entity
public class GenerateCredentialsEmail {

    private GenerateCredentialsEmail() {
    }

    public static String generateEmail(String lastName, String firstName) {

        Random random = null;
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return (lastName + "_"
                + firstName.charAt(0)
                + Objects.requireNonNull(random).nextInt(1, 9)
                + "@chpt.edu.ua").toLowerCase(Locale.ROOT);
    }

}
