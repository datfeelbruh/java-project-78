package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class NumberSchemaTest {
    private NumberSchema numberSchema;
    private final int positiveNumber = 3;
    private final int negativeNumber = -3;
    private final int startRange = 3;
    private final int endRange = 10;

    @BeforeEach
    public void beforeEach() {
        Validator v = new Validator();
        numberSchema = v.number();
    }

    @Test
    public void testWithNonConditions() {
        assertThat(numberSchema.isValid(null)).isEqualTo(true);
        assertThat(numberSchema.isValid(positiveNumber)).isEqualTo(true);
        assertThat(numberSchema.isValid(0)).isEqualTo(true);
        assertThat(numberSchema.isValid(negativeNumber)).isEqualTo(true);
    }
    @Test
    public void testWithRequiredCondition() {
        numberSchema.required();
        assertThat(numberSchema.isValid(null)).isEqualTo(false);
        assertThat(numberSchema.isValid(positiveNumber)).isEqualTo(true);
        assertThat(numberSchema.isValid("3")).isEqualTo(false);
    }
    @Test
    public void testWithPositiveCondition() {
        numberSchema.positive();
        assertThat(numberSchema.isValid(positiveNumber)).isEqualTo(true);
        assertThat(numberSchema.isValid(negativeNumber)).isEqualTo(false);
        assertThat(numberSchema.isValid(0)).isEqualTo(true);
        assertThat(numberSchema.isValid("3")).isEqualTo(false);
    }
    @Test
    public void testWithRangeCondition() {
        numberSchema.range(startRange, endRange);
        assertThat(numberSchema.isValid(positiveNumber)).isEqualTo(true);
        assertThat(numberSchema.isValid(negativeNumber)).isEqualTo(false);
        assertThat(numberSchema.isValid(endRange)).isEqualTo(true);
        assertThat(numberSchema.isValid(endRange + 1)).isEqualTo(false);
    }
    @Test
    public void testWithAllCondition() {
        numberSchema.required().positive().range(startRange, endRange);
        assertThat(numberSchema.isValid(startRange + 1)).isEqualTo(true);
        assertThat(numberSchema.isValid(null)).isEqualTo(false);
        assertThat(numberSchema.isValid(negativeNumber)).isEqualTo(false);
    }
}
