package kotetsu.auth.application.domain.service;

import org.springframework.stereotype.Service;

import kotetsu.auth.application.domain.entity.AuthUser;
import kotetsu.auth.application.domain.value.Password;

@Service
public class AuthUserService {
    public AuthUserService() {

    }

    public boolean isPasswordValid(AuthUser user, Password password) {
        return false;
    }
}
