package kotetsu.auth.application.dto;

import lombok.Getter;

public class EmailAndPasswordCheckOutput {
    @Getter
    private boolean isValid;

    @Getter
    private AuthUserProfileOutput profile;

    private EmailAndPasswordCheckOutput(boolean isValid, AuthUserProfileOutput profile) {
        this.isValid = isValid;
        this.profile = profile;
    }

    public static EmailAndPasswordCheckOutput of(
        final boolean isValid,
        final String userCode,
        final String name,
        final String email,
        final String imageUrl
    ) {
        return new EmailAndPasswordCheckOutput(
            isValid,
            AuthUserProfileOutput.of(
                userCode,
                name,
                email,
                imageUrl
            )
        );
    }
}
