package hexlet.code.schemes;

import java.util.Objects;

public final class NumberScheme extends BaseScheme<Integer> {
    public NumberScheme required() {
        addCondition(v -> !Objects.isNull(v));
        return this;
    }
    public NumberScheme positive() {
        addCondition(v -> v >= 0);
        return this;
    }
    public NumberScheme range(int start, int end) {
        addCondition(v -> v >= start && v <= end);
        return this;
    }
}
