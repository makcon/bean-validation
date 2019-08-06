package makcon.validation.rule;

import org.junit.Test;

import static org.junit.Assert.*;

public class NotBlankTest {

    @Test
    public void isValid_notBlank_valid() throws Exception {
        NotBlank rule = new NotBlank(() -> "notEmpty", null);

        assertTrue(rule.isValid());
    }

    @Test
    public void isValid_null_invalid() throws Exception {
        NotBlank rule = new NotBlank(() -> null, null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_empty_invalid() throws Exception {
        NotBlank rule = new NotBlank(() -> "", null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_blank_invalid() throws Exception {
        NotBlank rule = new NotBlank(() -> "   ", null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_invalidCheckMessage() throws Exception {
        NotBlank rule = new NotBlank(() -> "", "Field");

        assertEquals("'Field' can not be null, empty or blank", rule.getMessage());
    }
}