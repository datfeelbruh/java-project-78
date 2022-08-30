package hexlet.code;

import hexlet.code.schemes.MapScheme;
import hexlet.code.schemes.NumberScheme;
import hexlet.code.schemes.StringScheme;

public final class Validator {
    public StringScheme string() {
        return new StringScheme();
    }

    public NumberScheme number() {
        return new NumberScheme();
    }

    public MapScheme map() {
        return new MapScheme();
    }
}
