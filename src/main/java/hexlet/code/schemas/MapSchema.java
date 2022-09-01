package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {
    public MapSchema shape(Map<String, BaseSchema> map) {
        sizeof(map.size());

        for (Map.Entry<String, BaseSchema> entry : map.entrySet()) {
            Object key = entry.getKey();
            BaseSchema baseScheme = entry.getValue();
            addCondition(v -> ((Map<?, ?>) v).containsKey(key));
            addCondition(v -> baseScheme.isValid(((Map<?, ?>) v).get(key)));
        }
        return this;
    }
    public MapSchema required() {
        addCondition(v -> v instanceof Map<?, ?>);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCondition(v -> ((Map<?, ?>) v).size() == size);
        return this;
    }
}
