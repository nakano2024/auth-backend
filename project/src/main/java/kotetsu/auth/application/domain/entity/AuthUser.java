package kotetsu.auth.application.domain.entity;

import kotetsu.auth.application.domain.value.Code;
import kotetsu.auth.application.domain.value.Email;
import kotetsu.auth.application.domain.value.Password;
import kotetsu.auth.application.domain.value.UserImageUrl;
import kotetsu.auth.application.domain.value.UserName;
import lombok.Getter;

public class AuthUser {
    @Getter
    private final Code code;

    @Getter
    private final UserName name;

    @Getter
    private final Email email;

    @Getter
    private final Password password;

    @Getter
    private final UserImageUrl imageUrl;

    private AuthUser(Code code, UserName name, Email email, Password password, UserImageUrl imageUrl) {
        this.code = code;
        this.name = name;
        this.email = email;
        this.password = password;
        this.imageUrl = imageUrl;
    }

    public static AuthUser fetch(Code code , UserName name, Email email, Password password, UserImageUrl imageUrl) {
        return new AuthUser(
            code,
            name,
            email,
            password,
            imageUrl
        );
    }
}
