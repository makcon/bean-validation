package makcon.validation.rule;

import makcon.validation.message.Message;

import java.util.Collection;
import java.util.function.Supplier;

public class ElementNotBlank extends FieldRule {

    private final Supplier<Collection<String>> supplier;

    public ElementNotBlank(Supplier<Collection<String>> supplier,
                           String fieldName) {
        super(fieldName);
        this.supplier = supplier;
    }

    @Override
    public boolean isValid() {
        if (supplier.get() == null) {
            return true;
        }

        for (String object : supplier.get()) {
            if (object == null || object.trim().isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    Message.Pattern getPattern() {
        return Message.Pattern.ELEMENT_BLANK;
    }
}