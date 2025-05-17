package kotetsu.auth.application.usecase;

import kotetsu.auth.application.domain.entity.AuthUser;
import kotetsu.auth.application.domain.repository.IFetchAuthUserRepository;
import kotetsu.auth.application.domain.service.AuthUserService;
import kotetsu.auth.application.domain.value.Email;
import kotetsu.auth.application.domain.value.Password;
import kotetsu.auth.application.dto.EmailAndPasswordCheckInput;
import kotetsu.auth.application.dto.EmailAndPasswordCheckOutput;

public class CheckEmailAndPasswordUsecase {
    private final IFetchAuthUserRepository fetchAuthUserRepository;
    private final AuthUserService authUserService;

    public CheckEmailAndPasswordUsecase(
        IFetchAuthUserRepository fetchAuthUserRepository,
        AuthUserService authUserService
    ) {
        this.fetchAuthUserRepository = fetchAuthUserRepository;
        this.authUserService = authUserService;
    }

    public EmailAndPasswordCheckOutput isEmailAndPasswordValid(EmailAndPasswordCheckInput input) {
        AuthUser user = fetchAuthUserRepository.fetch(Email.of(input.getEmail()));
        if (user == null) {
            return EmailAndPasswordCheckOutput.of(
                false,
                "",
                "",
                "",
                ""
            );
        }

        if (!authUserService.isPasswordValid(user, Password.of(input.getPassword()))) {
            return EmailAndPasswordCheckOutput.of(
                false,
                user.getCode().getValue(),
                user.getName().getValue(),
                user.getEmail().getValue(),
                user.getImageUrl().getValue()
            );
        }

        return EmailAndPasswordCheckOutput.of(
            true,
            user.getCode().getValue(),
            user.getName().getValue(),
            user.getEmail().getValue(),
            user.getImageUrl().getValue()
        );
    }
}
