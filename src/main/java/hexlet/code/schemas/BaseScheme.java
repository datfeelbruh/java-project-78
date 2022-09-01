package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseScheme {
    private final List<Predicate> conditions = new ArrayList<>();

    public final void addCondition(Predicate condition) {
        conditions.add(condition);
    }

    public final boolean isValid(Object value) {
        return checkValue(value);
    }

    public final boolean checkValue(Object value) {
        for (Predicate condition : conditions) {
            if (!condition.test(value)) {
                return false;
            }
        }
        return true;
    }
}
