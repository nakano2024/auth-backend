package kotetsu.auth.application.domain.value;

import lombok.Getter;

public class Email {
    @Getter
    private String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email of(String value) {
        Email email = new Email(value);
        return email;
    }
}
