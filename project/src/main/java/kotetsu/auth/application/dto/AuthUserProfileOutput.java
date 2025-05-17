package kotetsu.auth.application.dto;

import lombok.Getter;

public class AuthUserProfileOutput {
    @Getter
    private String userCode;

    @Getter
    private String name;

    @Getter
    private String email;

    @Getter
    private String imageUrl;

    private AuthUserProfileOutput(String userCode, String name, String email, String imageUrl) {
        this.userCode = userCode;
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public static AuthUserProfileOutput of(String userCode, String name, String email, String imageUrl) {
        return new AuthUserProfileOutput(userCode, name, email, imageUrl);
    }
}
