package makcon.validation.rule;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaxTest {

    @Test
    public void isValid_lessValue_valid() throws Exception {
        Max rule = new Max(2, () -> 1, null);

        assertTrue(rule.isValid());
    }

    @Test
    public void isValid_null_valid() throws Exception {
        Max rule = new Max(1, () -> null, null);

        assertTrue(rule.isValid());
    }

    @Test
    public void isValid_equalsValue_valid() throws Exception {
        Max rule = new Max(1, () -> 1, null);

        assertTrue(rule.isValid());
    }

    @Test
    public void isValid_lessValue_invalid() throws Exception {
        Max rule = new Max(0, () -> 1, null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_lessValueFloat_invalid() throws Exception {
        Max rule = new Max(0.9f, () -> 1.0f, null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_lessValueLong_invalid() throws Exception {
        Max rule = new Max(0L, () -> 1L, null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_invalidCheckMessage() throws Exception {
        Max rule = new Max(0, () -> 1, "Field");

        assertEquals("'Field' must be equals or less than 0", rule.getMessage());
    }
}