package makcon.validation;

import java.util.List;

@FunctionalInterface
public interface Validated {

    default List<String> validate() {
        return getValidator().validate();
    }

    default Validator getValidator() {
        Validator validator = Validator.create();
        defineRules(validator);

        return validator;
    }

    void defineRules(Validator validator);
}
