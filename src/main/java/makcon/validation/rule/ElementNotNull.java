package makcon.validation.rule;

import makcon.validation.message.Message;

import java.util.Collection;
import java.util.function.Supplier;

public class ElementNotNull extends FieldRule {

    private final Supplier<Collection<?>> supplier;

    public ElementNotNull(Supplier<Collection<?>> supplier,
                          String fieldName) {
        super(fieldName);
        this.supplier = supplier;
    }

    @Override
    public boolean isValid() {
        if (supplier.get() == null) {
            return true;
        }

        for (Object object : supplier.get()) {
            if (object == null) {
                return false;
            }
        }

        return true;
    }

    @Override
    Message.Pattern getPattern() {
        return Message.Pattern.ELEMENT_NULL;
    }
}
