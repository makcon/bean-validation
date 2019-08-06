package makcon.validation.rule;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ElementNotEmptyTest {

    @Test
    public void isValid_elementsNotEmpty_valid() throws Exception {
        List<String> elements = Arrays.asList("NotBlank", " ");

        ElementNotEmpty rule = new ElementNotEmpty(() -> elements, null);

        assertTrue(rule.isValid());
    }

    @Test
    public void isValid_elementEmpty_invalid() throws Exception {
        List<String> elements = Arrays.asList("NotBlank", "");

        ElementNotEmpty rule = new ElementNotEmpty(() -> elements, null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_elementNull_invalid() throws Exception {
        List<String> elements = Arrays.asList("NotBlank", null);

        ElementNotEmpty rule = new ElementNotEmpty(() -> elements, null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_invalidCheckMessage() throws Exception {
        List<String> elements = Arrays.asList("NotBlank", null);

        ElementNotEmpty rule = new ElementNotEmpty(() -> elements, "Field");

        assertEquals("Element in 'Field' can not be null or empty", rule.getMessage());
    }
}