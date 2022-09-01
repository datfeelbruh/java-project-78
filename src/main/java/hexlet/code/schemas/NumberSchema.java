package hexlet.code.schemas;


public final class NumberSchema extends BaseSchema {

    public NumberSchema positive() {
        addCondition(v -> v == null || (v instanceof Integer && (Integer) v >= 0));
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
