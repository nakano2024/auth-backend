package kotetsu.auth.application.domain.value;

import lombok.Getter;

public class RawPassword {
    @Getter
    private final String value;

    public RawPassword(String value) {
        this.value = value;
    }

    public static RawPassword of(String value) {
        RawPassword password = new RawPassword(value);
        return password;
    }
}
