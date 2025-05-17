package kotetsu.auth.application.util;

public interface IHashedPasswordGenerator {
    String generate(String rawPassword);
}
