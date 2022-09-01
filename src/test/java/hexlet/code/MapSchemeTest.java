package hexlet.code;

import hexlet.code.schemes.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public final class MapSchemeTest {
    private MapSchema mapSchema;
    private final int size = 2;

    @BeforeEach
    public void beforeEach() {
        Validator v = new Validator();
        mapSchema = v.map();
    }

    @Test
    public void testWithoutCondition() {
        assertThat(mapSchema.isValid(null)).isEqualTo(true);
        assertThat(mapSchema.isValid(Map.of("key1", 1))).isEqualTo(true);
    }
    @Test
    public void testWithRequiredCondition() {
        mapSchema.required();
        assertThat(mapSchema.isValid(null)).isEqualTo(false);
    }
    @Test
    public void testWithSizeofCondition() {
        mapSchema.sizeof(size);
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        assertThat(mapSchema.isValid(map)).isEqualTo(false);
        map.put("key2", "value2");
        assertThat(mapSchema.isValid(map)).isEqualTo(true);
    }
    @Test
    public void testWithAllCondition() {
        mapSchema.required();
        assertThat(mapSchema.isValid(null)).isEqualTo(false);
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        assertThat(mapSchema.isValid(map)).isEqualTo(true);
        mapSchema.sizeof(size);
        assertThat(mapSchema.isValid(map)).isEqualTo(false);
        map.put("key2", "value2");
        assertThat(mapSchema.isValid(map)).isEqualTo(true);
    }
}
