package makcon.validation.rule;

import makcon.validation.message.Message;

import java.util.function.Supplier;

public class Max extends FieldRule {

    private final Supplier<Number> supplier;
    private final Number value;

    public Max(Number value,
               Supplier<Number> supplier,
               String fieldName) {
        super(fieldName, value);
        this.supplier = supplier;
        this.value = value;
    }

    @Override
    public boolean isValid() {
        if (supplier.get() == null) {
            return true;
        }

        return supplier.get().doubleValue() <= value.doubleValue();
    }

    @Override
    Message.Pattern getPattern() {
        return Message.Pattern.MAX;
    }
}
