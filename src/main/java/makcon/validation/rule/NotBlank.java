package makcon.validation.rule;

import makcon.validation.message.Message;

import java.util.function.Supplier;

public class NotBlank extends FieldRule {

    private final Supplier<String> supplier;

    public NotBlank(Supplier<String> supplier,
                    String fieldName) {
        super(fieldName);
        this.supplier = supplier;
    }

    @Override
    public boolean isValid() {
        return supplier.get() != null &&
                !supplier.get().trim().isEmpty();
    }

    @Override
    Message.Pattern getPattern() {
        return Message.Pattern.BLANK;
    }
}
