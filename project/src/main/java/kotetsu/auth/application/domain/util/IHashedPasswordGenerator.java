package kotetsu.auth.application.domain.util;

import kotetsu.auth.application.domain.value.HashedPassword;
import kotetsu.auth.application.domain.value.RawPassword;

public interface IHashedPasswordGenerator {
    HashedPassword generate(RawPassword rawPassword);
}
