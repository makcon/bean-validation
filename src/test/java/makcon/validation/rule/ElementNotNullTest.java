package makcon.validation.rule;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ElementNotNullTest {

    @Test
    public void isValid_elementsNotNull_valid() throws Exception {
        List<String> elements = Arrays.asList("NotBlank", "");

        ElementNotNull rule = new ElementNotNull(() -> elements, null);

        assertTrue(rule.isValid());
    }

    @Test
    public void isValid_elementsIntNotNull_valid() throws Exception {
        List<Integer> elements = Arrays.asList(1, 2);

        ElementNotNull rule = new ElementNotNull(() -> elements, null);

        assertTrue(rule.isValid());
    }

    @Test
    public void isValid_elementNull_invalid() throws Exception {
        List<String> elements = Arrays.asList("NotBlank", null);

        ElementNotNull rule = new ElementNotNull(() -> elements, null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_elementIntNull_invalid() throws Exception {
        List<Integer> elements = Arrays.asList(1, null);

        ElementNotNull rule = new ElementNotNull(() -> elements, null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_invalidCheckMessage() throws Exception {
        List<String> elements = Arrays.asList("NotBlank", null);

        ElementNotNull rule = new ElementNotNull(() -> elements, "Field");

        assertEquals("Element in 'Field' can not be null", rule.getMessage());
    }
}