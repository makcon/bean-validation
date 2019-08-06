package makcon.validation.rule;

import makcon.validation.message.Message;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

public class NotEmpty extends FieldRule {

    private final Supplier<?> supplier;

    public NotEmpty(Supplier<?> supplier,
                    String fieldName) {
        super(fieldName);
        this.supplier = supplier;
    }

    @Override
    public boolean isValid() {
        Object object = supplier.get();

        if (object == null) {
            return false;
        }
        if (object instanceof String) {
            return !((String) object).isEmpty();
        }
        if (object instanceof Collection) {
            return !((Collection<?>) object).isEmpty();
        }
        if (object instanceof Map) {
            return !((Map<?, ?>) object).isEmpty();
        }

        return true;
    }

    @Override
    Message.Pattern getPattern() {
        return Message.Pattern.EMPTY;
    }
}
