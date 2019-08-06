package makcon.validation.rule;

import org.junit.Test;

import static org.junit.Assert.*;

public class NotNullTest {

    @Test
    public void isValid_notNull_valid() throws Exception {
        NotNull rule = new NotNull(() -> "notEmpty", null);

        assertTrue(rule.isValid());
    }

    @Test
    public void isValid_null_invalid() throws Exception {
        NotNull rule = new NotNull(() -> null, null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_invalidCheckMessage() throws Exception {
        NotNull rule = new NotNull(() -> "", "Field");

        assertEquals("'Field' can not be null", rule.getMessage());
    }
}