package com.example.user.credentials.generate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import javax.persistence.criteria.CriteriaBuilder;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GenerateCredentialsEmailTest {

    @Test
    @DisplayName("Email generated not null")
    void notNullTrue_whenGenerateEmailCredential_thenReturnsGeneratedEmailTrue() {
        assertNotNull(GenerateCredentialsEmail.generateEmail("asd", "asd"));
    }

    @Test
    @DisplayName("Email credentials were generated")
    void setFirstNameAndLastName_whenGenerateEmailCredential_thenReturnsGeneratedEmail() {
        String actual = GenerateCredentialsEmail.generateEmail("Kud", "Denis");
        int i = Integer.parseInt(actual.replaceAll("\\D", ""));
        String generateEmail = ("Kud" + "_" + "Denis".charAt(0) + i
                + "@chpt.edu.ua").toLowerCase(Locale.ROOT);
        System.out.println("Actual: " + actual + "\n" + "Generated: " + generateEmail);
        assertEquals(generateEmail, actual);
    }
}