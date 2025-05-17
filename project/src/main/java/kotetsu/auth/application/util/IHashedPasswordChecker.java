package kotetsu.auth.application.util;

public interface IHashedPasswordChecker {
    boolean areEquals(String first, String second);
}
