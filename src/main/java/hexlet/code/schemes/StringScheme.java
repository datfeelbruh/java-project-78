package hexlet.code.schemes;

public final class StringScheme extends BaseScheme<String> {
    public StringScheme required() {
        addCondition(v -> v != null && !v.isEmpty());
        return this;
    }
    public StringScheme contains(String str) {
        addCondition(v -> v.contains(str));
        return this;
    }
    public StringScheme minLength(int length) {
        addCondition(v -> v.length() >= length);
        return this;
    }
}
