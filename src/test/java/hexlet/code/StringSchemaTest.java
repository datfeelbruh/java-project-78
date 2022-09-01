package hexlet.code;

import hexlet.code.schemes.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class StringSchemaTest {
    private StringSchema stringSchema;
    private final int minLength = 3;
    private final int number = 3;
    @BeforeEach
    public void beforeEach() {
        Validator v = new Validator();
        stringSchema = v.string();
    }
    @Test
    public void testWithNonConditions() {
        assertThat(stringSchema.isValid("")).isEqualTo(true);
        assertThat(stringSchema.isValid(null)).isEqualTo(true);
        assertThat(stringSchema.isValid("str")).isEqualTo(true);
        assertThat(stringSchema.isValid("123")).isEqualTo(true);
        assertThat(stringSchema.isValid(number)).isEqualTo(true);
    }
    @Test
    public void testWithRequiredCondition() {
        stringSchema.required();
        assertThat(stringSchema.isValid("")).isEqualTo(false);
        assertThat(stringSchema.isValid(null)).isEqualTo(false);
        assertThat(stringSchema.isValid("str")).isEqualTo(true);
        assertThat(stringSchema.isValid("123")).isEqualTo(true);
        assertThat(stringSchema.isValid(number)).isEqualTo(false);
    }
    @Test
    public void testWithMinLength() {
        stringSchema.minLength(minLength);
        assertThat(stringSchema.isValid("str")).isEqualTo(true);
        assertThat(stringSchema.isValid("12")).isEqualTo(false);
    }
    @Test
    public void testWithContains() {
        stringSchema.contains("s");
        assertThat(stringSchema.isValid("str")).isEqualTo(true);
        assertThat(stringSchema.isValid("12")).isEqualTo(false);
    }
    @Test
    public void testWithAllConditions() {
        stringSchema.required().minLength(minLength).contains("s");
        assertThat(stringSchema.isValid("")).isEqualTo(false);
        assertThat(stringSchema.isValid(null)).isEqualTo(false);
        assertThat(stringSchema.isValid("str")).isEqualTo(true);
        assertThat(stringSchema.isValid("st")).isEqualTo(false);
        assertThat(stringSchema.isValid("12")).isEqualTo(false);
    }
}
