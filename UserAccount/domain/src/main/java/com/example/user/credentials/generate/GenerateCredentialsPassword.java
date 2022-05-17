package com.example.user.credentials.generate;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Component
public class GenerateCredentialsPassword {
    private static final String CHARS_LOWERCASE
            = RandomStringUtils.randomAlphabetic(20).toLowerCase(Locale.ROOT);

    private static final String CHARS_UPPERCASE = CHARS_LOWERCASE.toUpperCase();
    private static final String DIGIT = RandomStringUtils.randomNumeric(9);
    private static final String OTHER_PUNCTUATION = "!@#&?*()";
    private static final String OTHER_SYMBOLS = "$^+=<>%_";
    private static final String OTHER_SPECIAL = OTHER_PUNCTUATION + OTHER_SYMBOLS;
    private static final int PASSWORD_LENGTH = 20;

    private static final String PASSWORD_ALLOW =
            CHARS_LOWERCASE + CHARS_UPPERCASE + DIGIT + OTHER_SPECIAL;

    private static final SecureRandom random = new SecureRandom();

    public static String generateStrongPassword() throws IllegalArgumentException {

        StringBuilder result = new StringBuilder(PASSWORD_LENGTH);

        String strLowerCase = generateRandomString(CHARS_LOWERCASE, 2);
        result.append(strLowerCase);

        String strUppercaseCase = generateRandomString(CHARS_UPPERCASE, 2);
        result.append(strUppercaseCase);

        String strDigit = generateRandomString(DIGIT, 2);
        result.append(strDigit);

        String strSpecialChar = generateRandomString(OTHER_SPECIAL, 2);
        result.append(strSpecialChar);

        String strOther = generateRandomString(PASSWORD_ALLOW, PASSWORD_LENGTH - 8);
        result.append(strOther);

        String password = result.toString();

        return shuffleObject(password);
    }

    private static String generateRandomString(
            String input,
            int size
    ) throws IllegalArgumentException {

        if (input == null || input.length() <= 0)
            throw new IllegalArgumentException("Invalid input.");
        if (size < 1) throw new IllegalArgumentException("Invalid size.");

        StringBuilder result = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            int index = random.nextInt(input.length());
            result.append(input.charAt(index));
        }
        return result.toString();
    }

    public static String shuffleObject(String input) {
        List<String> result = Arrays.asList(input.split(""));
        Collections.shuffle(result);
        Collections.shuffle(result);

        return String.join("", result);
    }
}
