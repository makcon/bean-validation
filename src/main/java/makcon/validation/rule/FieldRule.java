package makcon.validation.rule;

import makcon.validation.message.Message;

abstract class FieldRule implements Rule {

    private final Object[] params;

    FieldRule(Object... params) {
        this.params = params;
    }

    @Override
    public String getMessage() {
        return Message.get(getPattern(), params);
    }

    abstract Message.Pattern getPattern();
}
