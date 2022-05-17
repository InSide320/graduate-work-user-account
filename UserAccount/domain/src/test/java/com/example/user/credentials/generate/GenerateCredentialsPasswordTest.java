package com.example.user.credentials.generate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenerateCredentialsPasswordTest {

    @Test
    @DisplayName("Generated strong password not null")
    void checkForGeneration_whenGenerateStrongPassword_thenNotNull() {
        assertNotNull(GenerateCredentialsPassword.generateStrongPassword());
    }

    @Test
    @DisplayName("Password equal to given size")
    void whenGenerateStrongPassword_thenEqualToTheGivenSize() {
        assertEquals(20,
                GenerateCredentialsPassword.generateStrongPassword().length());
    }

    @Test
    @DisplayName("Check text blending")
    void testedForStirring_WhenShuffleObject_ThenNotEqualsLine() {
        String line = "Hello";
        assertNotEquals(line, GenerateCredentialsPassword.shuffleObject(line));
    }

}