package makcon.validation.rule;

import makcon.validation.message.Message;

import java.time.Instant;
import java.util.function.Supplier;

public class DateToGreaterOrEquals extends FieldRule {

    private final Supplier<Instant> from;
    private final Supplier<Instant> to;

    public DateToGreaterOrEquals(Supplier<Instant> from,
                                 Supplier<Instant> to,
                                 String fieldName) {
        super(fieldName, fieldName);
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isValid() {
        if (from.get() == null || to.get() == null) {
            return true;
        }

        return !from.get().isAfter(to.get());
    }

    @Override
    Message.Pattern getPattern() {
        return Message.Pattern.DATE_FROM_GREATER;
    }
}