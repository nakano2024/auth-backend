package kotetsu.auth.unit.checkemailandpasswordusecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import kotetsu.auth.application.domain.entity.AuthUser;
import kotetsu.auth.application.domain.repository.IFetchAuthUserRepository;
import kotetsu.auth.application.domain.service.AuthUserService;
import kotetsu.auth.application.domain.value.Code;
import kotetsu.auth.application.domain.value.Email;
import kotetsu.auth.application.domain.value.Password;
import kotetsu.auth.application.domain.value.UserImageUrl;
import kotetsu.auth.application.domain.value.UserName;
import kotetsu.auth.application.dto.EmailAndPasswordCheckInput;
import kotetsu.auth.application.dto.EmailAndPasswordCheckOutput;
import kotetsu.auth.application.usecase.CheckEmailAndPasswordUsecase;

@ExtendWith(MockitoExtension.class)
public class IsEmailAndPasswordValidTest {
    @Mock
    IFetchAuthUserRepository fetchAuthUserRepository;

    @Mock
    AuthUser authUser;

    @Mock
    Code userCode;

    @Mock
    Email email;

    @Mock
    Password inputPassword;

    @Mock
    UserName userName;

    @Mock
    UserImageUrl imageUrl;

    @Mock
    EmailAndPasswordCheckInput input;

    @Mock
    AuthUserService authUserService;

    CheckEmailAndPasswordUsecase checkEmailAndPasswordUsecase;

    @BeforeEach
    public void setUp() {
        checkEmailAndPasswordUsecase = new CheckEmailAndPasswordUsecase(
            fetchAuthUserRepository,
            authUserService
        );
    }

    @Test
    public void returnTrueWhenUserExistHavingCorrectEmailAndPassword() {
        try(
            final MockedStatic<EmailAndPasswordCheckOutput> outputStaticMock = mockStatic(EmailAndPasswordCheckOutput.class);
            final MockedStatic<Password> passwordStaticMock = mockStatic(Password.class);
        ) {
            when(userCode.getValue()).thenReturn("7d625f35-9a0c-b31b-03ac-d729cc53460a");
            when(email.getValue()).thenReturn("test@example.com");
            when(userName.getValue()).thenReturn("田中太郎");
            when(imageUrl.getValue()).thenReturn("https://example.com/image/user/9316e38b-4610-93ae-c817-0976fb470a2a");

            when(authUser.getCode()).thenReturn(userCode);
            when(authUser.getName()).thenReturn(userName);
            when(authUser.getEmail()).thenReturn(email);
            when(authUser.getImageUrl()).thenReturn(imageUrl);

            when(fetchAuthUserRepository.fetch(any())).thenReturn(authUser);

            when(authUserService.isPasswordValid(any(), any())).thenReturn(true);

            // 今回はinputの値に左右されないテストのためから文字を格納
            when(input.getEmail()).thenReturn("");
            when(input.getPassword()).thenReturn("");

            passwordStaticMock.when(() -> Password.of(anyString())).thenReturn(inputPassword);

            checkEmailAndPasswordUsecase.isEmailAndPasswordValid(input);

            ArgumentCaptor<Boolean> isValidCaptor = ArgumentCaptor.forClass(Boolean.class);
            ArgumentCaptor<String> userCodeCaptor = ArgumentCaptor.forClass(String.class);
            ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
            ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
            ArgumentCaptor<String> imageUrlCaptor = ArgumentCaptor.forClass(String.class);
            outputStaticMock.verify(() -> EmailAndPasswordCheckOutput.of(
                isValidCaptor.capture(),
                userCodeCaptor.capture(),
                nameCaptor.capture(),
                emailCaptor.capture(),
                imageUrlCaptor.capture()
            ));

            assertTrue(isValidCaptor.getValue());
            assertEquals("7d625f35-9a0c-b31b-03ac-d729cc53460a", userCodeCaptor.getValue());
            assertEquals("test@example.com", emailCaptor.getValue());
            assertEquals("田中太郎", nameCaptor.getValue());
            assertEquals("https://example.com/image/user/9316e38b-4610-93ae-c817-0976fb470a2a", imageUrlCaptor.getValue());
        }
    }

    @Test
    public void returnFalseWhenUserNotExist() {
        try(MockedStatic<EmailAndPasswordCheckOutput> outputStaticMock = mockStatic(EmailAndPasswordCheckOutput.class)) {
            when(fetchAuthUserRepository.fetch(any())).thenReturn(null);
            checkEmailAndPasswordUsecase.isEmailAndPasswordValid(input);

            ArgumentCaptor<Boolean> isValidCaptor = ArgumentCaptor.forClass(Boolean.class);
            ArgumentCaptor<String> userCodeCaptor = ArgumentCaptor.forClass(String.class);
            ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
            ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
            ArgumentCaptor<String> imageUrlCaptor = ArgumentCaptor.forClass(String.class);
            outputStaticMock.verify(() -> EmailAndPasswordCheckOutput.of(
                isValidCaptor.capture(),
                userCodeCaptor.capture(),
                nameCaptor.capture(),
                emailCaptor.capture(),
                imageUrlCaptor.capture()
            ));

            assertFalse(isValidCaptor.getValue());
            assertEquals("", userCodeCaptor.getValue());
            assertEquals("", emailCaptor.getValue());
            assertEquals("", nameCaptor.getValue());
            assertEquals("", imageUrlCaptor.getValue());
        }
    }

    @Test
    public void returnFalseWhenPasswordIncorrect() {
        try(
            MockedStatic<EmailAndPasswordCheckOutput> outputStaticMock = mockStatic(EmailAndPasswordCheckOutput.class);
            MockedStatic<Password> passwordStaticMock = mockStatic(Password.class);
        ) {
            when(userCode.getValue()).thenReturn("7d625f35-9a0c-b31b-03ac-d729cc53460a");
            when(email.getValue()).thenReturn("test@example.com");
            when(userName.getValue()).thenReturn("田中太郎");
            when(imageUrl.getValue()).thenReturn("https://example.com/image/user/9316e38b-4610-93ae-c817-0976fb470a2a");

            when(authUser.getCode()).thenReturn(userCode);
            when(authUser.getName()).thenReturn(userName);
            when(authUser.getEmail()).thenReturn(email);
            when(authUser.getImageUrl()).thenReturn(imageUrl);

            when(fetchAuthUserRepository.fetch(any())).thenReturn(authUser);

            passwordStaticMock.when(() -> Password.of(anyString())).thenReturn(inputPassword);

            // 今回はinputの値に左右されないテストのためから文字を格納
            when(input.getEmail()).thenReturn("");
            when(input.getPassword()).thenReturn("");

            when(authUserService.isPasswordValid(any(), any())).thenReturn(false);

            checkEmailAndPasswordUsecase.isEmailAndPasswordValid(input);

            ArgumentCaptor<Boolean> isValidCaptor = ArgumentCaptor.forClass(Boolean.class);
            ArgumentCaptor<String> userCodeCaptor = ArgumentCaptor.forClass(String.class);
            ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
            ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
            ArgumentCaptor<String> imageUrlCaptor = ArgumentCaptor.forClass(String.class);
            outputStaticMock.verify(() -> EmailAndPasswordCheckOutput.of(
                isValidCaptor.capture(),
                userCodeCaptor.capture(),
                nameCaptor.capture(),
                emailCaptor.capture(),
                imageUrlCaptor.capture()
            ));

            assertFalse(isValidCaptor.getValue());
            assertEquals("7d625f35-9a0c-b31b-03ac-d729cc53460a", userCodeCaptor.getValue());
            assertEquals("test@example.com", emailCaptor.getValue());
            assertEquals("田中太郎", nameCaptor.getValue());
            assertEquals("https://example.com/image/user/9316e38b-4610-93ae-c817-0976fb470a2a", imageUrlCaptor.getValue());
        }
    }
}
