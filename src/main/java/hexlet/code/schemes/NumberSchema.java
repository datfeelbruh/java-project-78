package hexlet.code.schemes;


public final class NumberSchema extends BaseScheme {

    public NumberSchema positive() {
        addCondition(v -> v == null || (Integer) v >= 0);
        return this;
    }
    public NumberSchema range(int start, int end) {
        addCondition(v -> (Integer) v >= start && (Integer) v <= end);
        return this;
    }
    public NumberSchema required() {
        addCondition(v -> v instanceof Integer);
        return this;
    }
}
