package kotetsu.auth.application.dto;

import lombok.Getter;

public class UserCredentialsOutput {
    @Getter
    private final String userCode;

    @Getter
    private final String email;

    @Getter
    private final String hashedPassword;

    private UserCredentialsOutput(String userCode, String email, String hashedPassword) {
        this.userCode = userCode;
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    public static UserCredentialsOutput of(String userCode, String email, String hashedPassword) {
        return new UserCredentialsOutput(userCode, email, hashedPassword);
    }
}
