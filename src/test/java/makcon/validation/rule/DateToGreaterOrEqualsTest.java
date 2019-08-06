package makcon.validation.rule;

import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateToGreaterOrEqualsTest {

    @Test
    public void isValid_toGreater_valid() throws Exception {
        Instant from = Instant.now();
        Instant to = from.plusSeconds(1L);

        DateToGreaterOrEquals rule = new DateToGreaterOrEquals(() -> from, () -> to, null);

        assertTrue(rule.isValid());
    }

    @Test
    public void isValid_toEqualsFrom_valid() throws Exception {
        Instant from = Instant.now();
        Instant to = from;

        DateToGreaterOrEquals rule = new DateToGreaterOrEquals(() -> from, () -> to, null);

        assertTrue(rule.isValid());
    }

    @Test
    public void isValid_fromGreater_invalid() throws Exception {
        Instant from = Instant.now();
        Instant to = from.minusSeconds(1L);

        DateToGreaterOrEquals rule = new DateToGreaterOrEquals(() -> from, () -> to, null);

        assertFalse(rule.isValid());
    }

    @Test
    public void isValid_invalidCheckMessage() throws Exception {
        Instant from = Instant.now();
        Instant to = from.minusSeconds(1L);

        DateToGreaterOrEquals rule = new DateToGreaterOrEquals(() -> from, () -> to, "Date");

        assertEquals("'Date' to must be greater or equals than 'Date' from", rule.getMessage());
    }

    @Test
    public void isValid_toAndFromNull_valid() throws Exception {
        DateToGreaterOrEquals rule = new DateToGreaterOrEquals(() -> null, () -> null, null);

        assertTrue(rule.isValid());
    }
}