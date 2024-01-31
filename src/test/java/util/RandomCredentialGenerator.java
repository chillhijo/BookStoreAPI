package util;

import java.util.Random;
import java.util.UUID;

public class RandomCredentialGenerator {
    private static final String ALPHABETIC_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGIT_CHARACTERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-=_+[]{}|;:'\",.<>/?";

    private Random random;

    public RandomCredentialGenerator() {
        random = new Random();
    }

    public String generateRandomUsername() {
        return generateRandomString(ALPHABETIC_CHARACTERS, 8);
    }

    public String generateRandomPassword() {
        // Ensure at least one non-alphanumeric character, one digit, one uppercase, one lowercase,
        // one special character, and a minimum length of 8 characters.
        String password = generateRandomString(ALPHABETIC_CHARACTERS, 1) +
                generateRandomString(DIGIT_CHARACTERS, 1) +
                generateRandomString(ALPHABETIC_CHARACTERS.toLowerCase(), 1) +
                generateRandomString(ALPHABETIC_CHARACTERS.toUpperCase(), 1) +
                generateRandomString(SPECIAL_CHARACTERS, 1) +
                generateRandomString(ALPHABETIC_CHARACTERS + DIGIT_CHARACTERS + SPECIAL_CHARACTERS, 3);

        return shuffleString(password);
    }

    private String generateRandomString(String characters, int length) {
        StringBuilder randomString = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            randomString.append(characters.charAt(randomIndex));
        }
        return randomString.toString();
    }

    private String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }
}
