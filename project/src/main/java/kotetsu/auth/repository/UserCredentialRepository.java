package kotetsu.auth.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import kotetsu.auth.application.domain.entity.UserCredential;
import kotetsu.auth.application.domain.repository.IFetchUserCredentialByEmailRepository;
import kotetsu.auth.application.domain.value.Code;
import kotetsu.auth.application.domain.value.Email;
import kotetsu.auth.application.domain.value.HashedPassword;

@Component
public class UserCredentialRepository implements IFetchUserCredentialByEmailRepository {
    final NamedParameterJdbcTemplate jdbcTemplate;

    public UserCredentialRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserCredential fetchByEmail(Email email) {
        final String sql = """
            SELECT code, email, password FROM users WHERE email = :email
        """;

        Map<String, String> params = new HashMap<>();
        params.put("email", email.getValue());

        try {
            Map<String, Object> userRecord = jdbcTemplate.queryForMap(sql, params);

            return UserCredential.fetch(
                Code.of(userRecord.get("code").toString()),
                Email.of((String) userRecord.get("email")),
                HashedPassword.of((String) userRecord.get("password"))
            );
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
}
