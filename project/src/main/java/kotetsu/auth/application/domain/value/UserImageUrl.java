package kotetsu.auth.application.domain.value;

import lombok.Getter;

public class UserImageUrl {
    @Getter
    private final String value;

    private UserImageUrl(String value) {
        this.value = value;
    }

    static public UserImageUrl of(String value) {
        UserImageUrl imageUrl = new UserImageUrl(value);
        return imageUrl;
    }
}
