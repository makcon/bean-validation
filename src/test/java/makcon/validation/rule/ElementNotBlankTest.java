package makcon.validation.rule;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ElementNotBlankTest {

    @Test
    public void isValid_elementsNotBlank_valid() throws Exception {
        List<String> elements = Arrays.asList("NotBlank", "NotBlank2");

        ElementNotBlank rule = new ElementNotBlank(() -> elements, null);

        assertTrue(rule.isValid());
    }

    @Test
    public void isValid_elementBlank_invalid() throws Exception {
        List<String> elements = Arrays.asList("NotBlank", " ");

        ElementNotBlank rule = new ElementNotBlank(() -> elements, null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_elementEmpty_invalid() throws Exception {
        List<String> elements = Arrays.asList("NotBlank", "");

        ElementNotBlank rule = new ElementNotBlank(() -> elements, null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_elementNull_invalid() throws Exception {
        List<String> elements = Arrays.asList("NotBlank", null);

        ElementNotBlank rule = new ElementNotBlank(() -> elements, null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_invalidCheckMessage() throws Exception {
        List<String> elements = Arrays.asList("NotBlank", null);

        ElementNotBlank rule = new ElementNotBlank(() -> elements, "Field");

        assertEquals("Element in 'Field' can not be null, empty or blank", rule.getMessage());
    }
}