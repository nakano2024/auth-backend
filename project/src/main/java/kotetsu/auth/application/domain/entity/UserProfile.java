package kotetsu.auth.application.domain.entity;

import kotetsu.auth.application.domain.value.Code;
import kotetsu.auth.application.domain.value.Email;
import kotetsu.auth.application.domain.value.UserImageUrl;
import kotetsu.auth.application.domain.value.UserName;
import lombok.Getter;

public class UserProfile {
    @Getter
    Code code;

    @Getter
    UserName name;

    @Getter
    Email email;

    @Getter
    UserImageUrl imageUrl;

    private UserProfile(Code code, UserName name, Email email, UserImageUrl imageUrl) {
        this.code = code;
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public static UserProfile fetch(Code code, UserName name, Email email, UserImageUrl imageUrl) {
        return new UserProfile(
            code,
            name,
            email,
            imageUrl
        );
    }
}
