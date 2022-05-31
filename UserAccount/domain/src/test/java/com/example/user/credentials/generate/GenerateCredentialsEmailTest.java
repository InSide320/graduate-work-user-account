package com.example.user.credentials.generate;

import com.example.exception.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

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

    @Test
    @DisplayName("Validation exception by last name")
    void checkValidationException_whenLastNameNull_thenValidationException() {
        assertThrows(ValidationException.class,
                () -> GenerateCredentialsEmail.generateEmail("", "asdasd"));
    }
    @Test
    @DisplayName("Validation exception by first name")
    void checkValidationException_whenFirstNameNull_thenValidationException() {
        assertThrows(ValidationException.class,
                () -> GenerateCredentialsEmail.generateEmail("asdass", ""));
    }
}