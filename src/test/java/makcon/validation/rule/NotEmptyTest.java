package makcon.validation.rule;

import org.junit.Test;

import java.util.Collections;

import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

public class NotEmptyTest {

    @Test
    public void isValid_stringNotEmpty_valid() throws Exception {
        NotEmpty rule = new NotEmpty(() -> "notEmpty", null);

        assertTrue(rule.isValid());
    }

    @Test
    public void isValid_objectNull_invalid() throws Exception {
        NotEmpty rule = new NotEmpty(() -> null, null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_stringEmpty_invalid() throws Exception {
        NotEmpty rule = new NotEmpty(() -> "", null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_collectionNotEmpty_valid() throws Exception {
        NotEmpty rule = new NotEmpty(() -> singletonList("NotEmpty"), null);

        assertTrue(rule.isValid());
    }

    @Test
    public void isValid_collectionEmpty_valid() throws Exception {
        NotEmpty rule = new NotEmpty(Collections::emptyList, null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_mapEmpty_valid() throws Exception {
        NotEmpty rule = new NotEmpty(Collections::emptyMap, null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_invalidCheckMessage() throws Exception {
        NotEmpty rule = new NotEmpty(() -> "", "Field");

        assertEquals("'Field' can not be null or empty", rule.getMessage());
    }
}