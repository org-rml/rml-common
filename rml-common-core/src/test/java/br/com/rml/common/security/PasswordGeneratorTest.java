package br.com.rml.common.security;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PasswordGeneratorTest {

    @Test
    void generatesPasswordWithEveryRequiredCharacterGroup() {
        String password = PasswordGenerator.generateSecurePassword();

        assertThat(password).hasSize(PasswordGenerator.DEFAULT_LENGTH);
        assertThat(password).containsPattern("[A-Z]");
        assertThat(password).containsPattern("[a-z]");
        assertThat(password).containsPattern("[2-9]");
        assertThat(password).containsPattern("[!@#$%*+\\-_?]");
    }

    @Test
    void rejectsLengthBelowTheRequiredCharacterGroups() {
        assertThatThrownBy(() -> PasswordGenerator.generateSecurePassword(3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Password deve ter pelo menos 4 caracteres");
    }
}
