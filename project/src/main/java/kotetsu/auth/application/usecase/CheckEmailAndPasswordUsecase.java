package kotetsu.auth.application.usecase;

import kotetsu.auth.application.domain.entity.AuthUser;
import kotetsu.auth.application.domain.repository.IFetchAuthUserRepository;
import kotetsu.auth.application.domain.value.Email;
import kotetsu.auth.application.dto.EmailAndPasswordCheckInput;
import kotetsu.auth.application.dto.EmailAndPasswordCheckOutput;
import kotetsu.auth.application.util.IHashedPasswordChecker;
import kotetsu.auth.application.util.IHashedPasswordGenerator;

public class CheckEmailAndPasswordUsecase {
    private final IFetchAuthUserRepository fetchAuthUserRepository;
    private final IHashedPasswordGenerator hashedPasswordGenerator;
    private final IHashedPasswordChecker hashedPasswordChecker;

    public CheckEmailAndPasswordUsecase(
        IFetchAuthUserRepository fetchAuthUserRepository,
        IHashedPasswordGenerator hashedPasswordGenerator,
        IHashedPasswordChecker hashedPasswordChecker
    ) {
        this.fetchAuthUserRepository = fetchAuthUserRepository;
        this.hashedPasswordGenerator = hashedPasswordGenerator;
        this.hashedPasswordChecker = hashedPasswordChecker;
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

        if (!hashedPasswordChecker.areEquals(
            user.getPassword().getValue(),
            hashedPasswordGenerator.generate(input.getPassword())
        )) {
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
