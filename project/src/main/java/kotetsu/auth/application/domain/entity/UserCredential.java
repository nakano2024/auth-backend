package kotetsu.auth.application.domain.entity;

import kotetsu.auth.application.domain.value.Code;
import kotetsu.auth.application.domain.value.Email;
import kotetsu.auth.application.domain.value.HashedPassword;
import lombok.Getter;

public class UserCredential {
    @Getter
    private final Code code;

    @Getter
    private final Email email;

    @Getter
    private final HashedPassword password;

    private UserCredential(Code code, Email email, HashedPassword password) {
        this.code = code;
        this.email = email;
        this.password = password;
    }

    public static UserCredential fetch(Code code , Email email, HashedPassword password) {
        return new UserCredential(
            code,
            email,
            password
        );
    }
}
