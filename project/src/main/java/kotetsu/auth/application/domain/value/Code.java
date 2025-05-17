package kotetsu.auth.application.domain.value;

import lombok.Getter;

public class Code {
    @Getter
    private final String value;

    private Code(String value) {
        this.value = value;
    }

    public static Code of(String value) {
        Code code = new Code(value);
        return code;
    }

    public boolean equalTo(Code another) {
        return value.equals(another.getValue());
    }
}
