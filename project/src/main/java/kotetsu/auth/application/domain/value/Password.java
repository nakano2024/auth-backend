package kotetsu.auth.application.domain.value;

import lombok.Getter;

public class Password {
    @Getter
    String value;

    public Password(String value) {
        this.value = value;
    }

    public static Password of(String value) {
        Password password = new Password(value);
        return password;
    }
}
