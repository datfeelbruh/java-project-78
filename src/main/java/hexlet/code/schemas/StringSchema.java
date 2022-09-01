package hexlet.code.schemas;

import java.util.Objects;

public final class StringSchema extends BaseSchema {
    public StringSchema required() {
        addCondition(v -> v instanceof String && !Objects.equals(v, ""));
        return this;
    }
    public StringSchema contains(String str) {
        addCondition(v -> v instanceof String && ((String) v).contains(str));
        return this;
    }
    public StringSchema minLength(int length) {
        addCondition(v -> v instanceof String && ((String) v).length() >= length);
        return this;
    }
}
