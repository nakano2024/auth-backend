package kotetsu.auth.application.dto;

import lombok.Getter;

public class UserCredentialsOutput {
    @Getter
    private final String userCode;

    @Getter
    private final String name;

    @Getter
    private final String email;

    @Getter
    private final String hashedPassword;

    @Getter
    private final String imageUrl;

    private UserCredentialsOutput(String userCode, String name, String email, String hashedPassword, String imageUrl) {
        this.userCode = userCode;
        this.name = name;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.imageUrl = imageUrl;
    }

    public static UserCredentialsOutput of(String userCode, String name, String email, String hashedPassword, String imageUrl) {
        return new UserCredentialsOutput(userCode, name, email, hashedPassword, imageUrl);
    }
}
