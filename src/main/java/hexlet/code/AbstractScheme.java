package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractScheme<T> {
    private final List<Predicate<T>> conditions = new ArrayList<>();

    public final void addCondition(Predicate<T> condition) {
        conditions.add(condition);
    }

    public final boolean isValid(T value) {
        return areConditionSet(value);
    }

    public final boolean areConditionSet(T value) {
        for (Predicate<T> condition : conditions) {
            if (!condition.test(value)) {
                return false;
            }
        }
        return true;
    }
}
