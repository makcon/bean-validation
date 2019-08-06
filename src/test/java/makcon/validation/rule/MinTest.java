package makcon.validation.rule;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MinTest {

    @Test
    public void isValid_moreValue_valid() throws Exception {
        Min rule = new Min(1, () -> 2, null);

        assertTrue(rule.isValid());
    }

    @Test
    public void isValid_null_valid() throws Exception {
        Min rule = new Min(1, () -> null, null);

        assertTrue(rule.isValid());
    }

    @Test
    public void isValid_equalsValue_valid() throws Exception {
        Min rule = new Min(1, () -> 1, null);

        assertTrue(rule.isValid());
    }

    @Test
    public void isValid_lessValue_invalid() throws Exception {
        Min rule = new Min(1, () -> 0, null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_lessValueFloat_invalid() throws Exception {
        Min rule = new Min(1.0f, () -> 0.9f, null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_lessValueLong_invalid() throws Exception {
        Min rule = new Min(1L, () -> 0L, null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_invalidCheckMessage() throws Exception {
        Min rule = new Min(1, () -> 0, "Field");

        assertEquals("'Field' must be equals or more than 1", rule.getMessage());
    }
}