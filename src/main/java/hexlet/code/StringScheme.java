package hexlet.code;

public final class StringScheme extends AbstractScheme<String> {
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
