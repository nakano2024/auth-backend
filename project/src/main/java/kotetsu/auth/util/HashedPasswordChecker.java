package kotetsu.auth.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import kotetsu.auth.application.domain.util.IHashedPasswordChecker;
import kotetsu.auth.application.domain.value.HashedPassword;
import kotetsu.auth.application.domain.value.RawPassword;

@Component
public class HashedPasswordChecker implements IHashedPasswordChecker {
    PasswordEncoder passwordEncoder;

    @Override
    public boolean areEquals(RawPassword rawPassword, HashedPassword hashedPassword) {
        return passwordEncoder.matches(rawPassword.getValue(), hashedPassword.getValue());
    }
}
