package kotetsu.auth.application.domain.value;

import lombok.Getter;

public class HashedPassword {
    @Getter
    String value;

    public HashedPassword(String value) {
        this.value = value;
    }

    public static HashedPassword of(String value) {
        HashedPassword password = new HashedPassword(value);
        return password;
    }
}
