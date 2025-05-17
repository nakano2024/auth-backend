package kotetsu.auth.application.domain.exception;

public class AuthUserNotFoundException extends RuntimeException {
    public AuthUserNotFoundException() {
        super("AuthUser Not Found");
    }
}
