package kotetsu.auth.application.dto;

import lombok.Getter;

public class EmailAndPasswordCheckInput {
    @Getter
    private String email;

    @Getter
    private String password;

    private EmailAndPasswordCheckInput(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static EmailAndPasswordCheckInput of(String email, String password) {
        EmailAndPasswordCheckInput input = new EmailAndPasswordCheckInput(email, password);
        return input;
    }
}
