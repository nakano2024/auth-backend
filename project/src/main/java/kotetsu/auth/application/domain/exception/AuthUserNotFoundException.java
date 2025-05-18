package kotetsu.auth.application.domain.exception;

import java.io.IOException;

public class AuthUserNotFoundException extends IOException {
    public AuthUserNotFoundException() {
        super("AuthUser Not Found");
    }
}
