package kotetsu.auth.unit.authuserservice;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import kotetsu.auth.application.domain.entity.AuthUser;
import kotetsu.auth.application.domain.service.AuthUserService;
import kotetsu.auth.application.domain.util.IHashedPasswordChecker;
import kotetsu.auth.application.domain.util.IHashedPasswordGenerator;
import kotetsu.auth.application.domain.value.HashedPassword;
import kotetsu.auth.application.domain.value.RawPassword;

@ExtendWith(MockitoExtension.class)
public class IsPasswordValidTest {
    @Mock
    IHashedPasswordChecker hashedPasswordChecker;

    @Mock
    IHashedPasswordGenerator hashedPasswordGenerator;

    @Mock
    RawPassword inputPassword;

    @Mock
    HashedPassword inputHashedPassword;

    @Mock
    HashedPassword userPassword;

    @Mock
    AuthUser user;

    AuthUserService userService;

    @BeforeEach
    public void setUp() {
        userService = new AuthUserService(hashedPasswordChecker);
    }

    @Test
    public void returnTrueIfPasswordValid() {
        when(hashedPasswordChecker.areEquals(any(), any())).thenReturn(true);
        assertTrue(userService.isPasswordValid(inputPassword, user));
    }

    @Test
    public void returnFalseIfPasswordInvalid() {
        when(hashedPasswordChecker.areEquals(any(), any())).thenReturn(false);
        assertFalse(userService.isPasswordValid(inputPassword, user));
    }
}
