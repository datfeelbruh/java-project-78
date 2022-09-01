package hexlet.code;

import hexlet.code.schemes.BaseScheme;
import hexlet.code.schemes.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

public final class MapSchemaShapeTest {
    private MapSchema schema;
    private final int number = 100;

    @BeforeEach
    public void beforeEach() {
        Validator v = new Validator();
        schema = v.map();
        Map<String, BaseScheme> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);
    }
    @Test
    public void testShape1() {
        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", number);
        assertThat(schema.isValid(human1)).isEqualTo(true);
    }
    @Test
    public void testShape2() {
        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertThat(schema.isValid(human2)).isEqualTo(true);
    }
    @Test
    public void testShape3() {
        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertThat(schema.isValid(human3)).isEqualTo(false);
    }
    @Test
    public void testShape4() {
        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", number - (number + 1));
        assertThat(schema.isValid(human4)).isEqualTo(false);
    }
}
