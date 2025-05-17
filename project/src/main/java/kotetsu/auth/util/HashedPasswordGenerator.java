package kotetsu.auth.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import kotetsu.auth.application.domain.util.IHashedPasswordGenerator;
import kotetsu.auth.application.domain.value.HashedPassword;
import kotetsu.auth.application.domain.value.RawPassword;

@Component
public class HashedPasswordGenerator implements IHashedPasswordGenerator {
    PasswordEncoder passwordEncoder;

    public HashedPasswordGenerator(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public HashedPassword generate(RawPassword rawPassword) {
        return HashedPassword.of(passwordEncoder.encode(rawPassword.getValue()));
    }
}
