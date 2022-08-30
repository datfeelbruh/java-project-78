package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class ValidatorTest {
    public static class StringSchemeTest {
        StringScheme scheme;
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
            scheme.minLength(3);
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
            scheme.required().minLength(3).contains("s");
            assertThat(scheme.isValid("")).isEqualTo(false);
            assertThat(scheme.isValid(null)).isEqualTo(false);
            assertThat(scheme.isValid("str")).isEqualTo(true);
            assertThat(scheme.isValid("st")).isEqualTo(false);
            assertThat(scheme.isValid("12")).isEqualTo(false);

        }
    }
}
