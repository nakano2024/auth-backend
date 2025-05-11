package kotetsu.auth.application.domain.repository;

import kotetsu.auth.application.domain.entity.AuthUser;
import kotetsu.auth.application.domain.value.Email;

public interface IFetchAuthUserRepository {
    AuthUser fetch(Email email);
}
