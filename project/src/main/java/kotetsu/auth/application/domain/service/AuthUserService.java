package kotetsu.auth.application.domain.service;

import kotetsu.auth.application.domain.entity.AuthUser;
import kotetsu.auth.application.domain.util.IHashedPasswordChecker;
import kotetsu.auth.application.domain.value.RawPassword;

public class AuthUserService {
    private final IHashedPasswordChecker hashedPasswordChecker;

    public AuthUserService(IHashedPasswordChecker hashedPasswordChecker) {
        this.hashedPasswordChecker = hashedPasswordChecker;
    }

    public boolean isPasswordValid(RawPassword inputPassword, AuthUser user) {
        return hashedPasswordChecker.areEquals(inputPassword, user.getPassword());
    }
}
