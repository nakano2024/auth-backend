package kotetsu.auth.application.domain.exception;

import java.io.IOException;

public class UserCredentialNotFoundException extends IOException {
    public UserCredentialNotFoundException() {
        super("UserCredential Not Found");
    }
}
