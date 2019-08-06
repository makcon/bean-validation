package makcon.validation.rule;

import makcon.validation.message.Message;

import java.util.function.Supplier;

public class NotNull extends FieldRule {

    private final Supplier<?> supplier;

    public NotNull(Supplier<?> supplier,
                   String fieldName) {
        super(fieldName);
        this.supplier = supplier;
    }

    @Override
    public boolean isValid() {
        return supplier.get() != null;
    }

    @Override
    Message.Pattern getPattern() {
        return Message.Pattern.NULL;
    }
}
