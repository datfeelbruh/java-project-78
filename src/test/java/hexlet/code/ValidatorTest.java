package hexlet.code;

import hexlet.code.schemes.MapScheme;
import hexlet.code.schemes.NumberScheme;
import hexlet.code.schemes.StringScheme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public final class ValidatorTest {
    public static final class StringSchemeTest {
        private StringScheme scheme;
        private final int minLength = 3;
        @BeforeEach
        public void beforeEach() {
            Validator v = new Validator();
            scheme = v.string();
        }
        @Test
        public void testWithNonConditions() {
            assertThat(scheme.isValid("")).isEqualTo(true);
            assertThat(scheme.isValid(null)).isEqualTo(true);
            assertThat(scheme.isValid("str")).isEqualTo(true);
            assertThat(scheme.isValid("123")).isEqualTo(true);
        }
        @Test
        public void testWithRequiredCondition() {
            scheme.required();
            assertThat(scheme.isValid("")).isEqualTo(false);
            assertThat(scheme.isValid(null)).isEqualTo(false);
            assertThat(scheme.isValid("str")).isEqualTo(true);
            assertThat(scheme.isValid("123")).isEqualTo(true);
        }
        @Test
        public void testWithMinLength() {
            scheme.minLength(minLength);
            assertThat(scheme.isValid("str")).isEqualTo(true);
            assertThat(scheme.isValid("12")).isEqualTo(false);
        }
        @Test
        public void testWithContains() {
            scheme.contains("s");
            assertThat(scheme.isValid("str")).isEqualTo(true);
            assertThat(scheme.isValid("12")).isEqualTo(false);
        }
        @Test
        public void testWithAllConditions() {
            scheme.required().minLength(minLength).contains("s");
            assertThat(scheme.isValid("")).isEqualTo(false);
            assertThat(scheme.isValid(null)).isEqualTo(false);
            assertThat(scheme.isValid("str")).isEqualTo(true);
            assertThat(scheme.isValid("st")).isEqualTo(false);
            assertThat(scheme.isValid("12")).isEqualTo(false);

        }
    }

    public static final class NumberSchemeTest {
        private NumberScheme numberScheme;
        private final int start = 3;
        private final int end = 10;

        @BeforeEach
        public void beforeEach() {
            Validator v = new Validator();
            numberScheme = v.number();
        }

        @Test
        public void testWithNonConditions() {
            assertThat(numberScheme.isValid(null)).isEqualTo(true);
            assertThat(numberScheme.isValid(3)).isEqualTo(true);
            assertThat(numberScheme.isValid(0)).isEqualTo(true);
            assertThat(numberScheme.isValid(-3)).isEqualTo(true);
        }
        @Test
        public void testWithRequiredCondition() {
            numberScheme.required();
            assertThat(numberScheme.isValid(null)).isEqualTo(false);
            assertThat(numberScheme.isValid(3)).isEqualTo(true);
        }
        @Test
        public void testWithPositiveCondition() {
            numberScheme.positive();
            assertThat(numberScheme.isValid(3)).isEqualTo(true);
            assertThat(numberScheme.isValid(-3)).isEqualTo(false);
            assertThat(numberScheme.isValid(0)).isEqualTo(true);
        }
        @Test
        public void testWithRangeCondition() {
            numberScheme.range(start, end);
            assertThat(numberScheme.isValid(3)).isEqualTo(true);
            assertThat(numberScheme.isValid(-3)).isEqualTo(false);
            assertThat(numberScheme.isValid(10)).isEqualTo(true);
            assertThat(numberScheme.isValid(11)).isEqualTo(false);
        }
        @Test
        public void testWithAllCondition() {
            numberScheme.required().positive().range(start, end);
            assertThat(numberScheme.isValid(4)).isEqualTo(true);
            assertThat(numberScheme.isValid(null)).isEqualTo(false);
            assertThat(numberScheme.isValid(-3)).isEqualTo(false);
        }
    }

    public static final class MapSchemeTest {
        private MapScheme mapScheme;
        private final int size = 2;

        @BeforeEach
        public void beforeEach() {
            Validator v = new Validator();
            mapScheme = v.map();
        }

        @Test
        public void testWithoutCondition() {
            assertThat(mapScheme.isValid(null)).isEqualTo(true);
            assertThat(mapScheme.isValid(Map.of("l", 3))).isEqualTo(true);
        }
        @Test
        public void testWithRequiredCondition() {
            mapScheme.required();
            assertThat(mapScheme.isValid(null)).isEqualTo(false);
        }
        @Test
        public void testWithSizeofCondition() {
            mapScheme.sizeof(size);
            Map<String, String> map = new HashMap<>();
            map.put("key1", "value1");
            assertThat(mapScheme.isValid(map)).isEqualTo(false);
            map.put("key2", "value2");
            assertThat(mapScheme.isValid(map)).isEqualTo(true);
        }
        @Test
        public void testWithAllCondition() {
            mapScheme.required();
            assertThat(mapScheme.isValid(null)).isEqualTo(false);
            Map<String, String> map = new HashMap<>();
            map.put("key1", "value1");
            assertThat(mapScheme.isValid(map)).isEqualTo(true);
            mapScheme.sizeof(size);
            assertThat(mapScheme.isValid(map)).isEqualTo(false);
            map.put("key2", "value2");
            assertThat(mapScheme.isValid(map)).isEqualTo(true);
        }
    }
}
