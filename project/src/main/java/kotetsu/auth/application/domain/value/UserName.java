package kotetsu.auth.application.domain.value;

import lombok.Getter;

public class UserName {
    @Getter
    String value;

    private UserName(String value) {
        this.value = value;
    }

    static public UserName of(String value) {
        UserName userName = new UserName(value);
        return userName;
    }
}
