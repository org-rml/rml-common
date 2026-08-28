package br.com.rml.common.security;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generates passwords using a cryptographically secure random source.
 */
public final class PasswordGenerator {

    public static final int DEFAULT_LENGTH = 12;
    public static final int MINIMUM_LENGTH = 4;

    private static final String UPPERCASE = "ABCDEFGHJKLMNPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijkmnopqrstuvwxyz";
    private static final String DIGITS = "23456789";
    private static final String SYMBOLS = "!@#$%*+-_?";
    private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS + SYMBOLS;
    private static final SecureRandom RANDOM = new SecureRandom();

    private PasswordGenerator() {
    }

    public static String generateSecurePassword() {
        return generateSecurePassword(DEFAULT_LENGTH);
    }

    /**
     * Generates a password containing at least one uppercase letter, lowercase letter,
     * digit and symbol. Ambiguous characters are intentionally excluded.
     *
     * @param length password length, at least {@value MINIMUM_LENGTH}
     * @return a cryptographically secure password
     */
    public static String generateSecurePassword(int length) {
        if (length < MINIMUM_LENGTH) {
            throw new IllegalArgumentException("Password deve ter pelo menos " + MINIMUM_LENGTH + " caracteres");
        }

        List<Character> characters = new ArrayList<>(length);
        characters.add(randomCharacter(UPPERCASE));
        characters.add(randomCharacter(LOWERCASE));
        characters.add(randomCharacter(DIGITS));
        characters.add(randomCharacter(SYMBOLS));

        while (characters.size() < length) {
            characters.add(randomCharacter(ALL_CHARACTERS));
        }

        Collections.shuffle(characters, RANDOM);
        StringBuilder password = new StringBuilder(length);
        characters.forEach(password::append);
        return password.toString();
    }

    private static char randomCharacter(String characters) {
        return characters.charAt(RANDOM.nextInt(characters.length()));
    }
}
