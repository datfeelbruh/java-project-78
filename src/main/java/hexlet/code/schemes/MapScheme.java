package hexlet.code.schemes;

import java.util.Map;
import java.util.Objects;

public final class MapScheme extends BaseScheme<Map> {
    public MapScheme required() {
        addCondition(Objects::nonNull);
        return this;
    }

    public MapScheme sizeof(int size) {
        addCondition(v -> v.size() == size);
        return this;
    }
}
