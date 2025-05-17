package kotetsu.auth.application.dto;

import lombok.Getter;

public class GetUserCredentialsInput {
    @Getter
    private final String email;

    private GetUserCredentialsInput(String email) {
        this.email = email;
    }

    public static GetUserCredentialsInput of(String email) {
        return new GetUserCredentialsInput(email);
    }
}
