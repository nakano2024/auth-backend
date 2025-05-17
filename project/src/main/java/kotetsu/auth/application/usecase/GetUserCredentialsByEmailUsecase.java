package kotetsu.auth.application.usecase;

import kotetsu.auth.application.domain.entity.AuthUser;
import kotetsu.auth.application.domain.exception.AuthUserNotFoundException;
import kotetsu.auth.application.domain.repository.IFetchAuthUserByEmailRepository;
import kotetsu.auth.application.domain.value.Email;
import kotetsu.auth.application.dto.GetUserCredentialsInput;
import kotetsu.auth.application.dto.UserCredentialsOutput;


public class GetUserCredentialsByEmailUsecase {
    private final IFetchAuthUserByEmailRepository fetchAuthUserRepository;

    public GetUserCredentialsByEmailUsecase(IFetchAuthUserByEmailRepository fetchAuthUserRepository) {
        this.fetchAuthUserRepository = fetchAuthUserRepository;
    }

    public UserCredentialsOutput getUserCredentials(GetUserCredentialsInput input) {
        AuthUser user = fetchAuthUserRepository.fetchByEmail(Email.of(input.getEmail()));

        if (user == null) {
            throw new AuthUserNotFoundException();
        }

        return UserCredentialsOutput.of(
            user.getCode().getValue(),
            user.getName().getValue(),
            user.getEmail().getValue(),
            user.getPassword().getValue(),
            user.getImageUrl().getValue()
        );
    }
}
