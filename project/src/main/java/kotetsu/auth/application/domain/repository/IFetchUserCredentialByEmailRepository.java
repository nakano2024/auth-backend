package kotetsu.auth.application.domain.repository;

import kotetsu.auth.application.domain.entity.UserCredential;
import kotetsu.auth.application.domain.value.Email;

public interface IFetchUserCredentialByEmailRepository {
    UserCredential fetchByEmail(Email email);
}
