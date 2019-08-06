package makcon.validation;

import lombok.Data;

@Data
public class TestObjectParent implements Validated {

    @Override
    public void defineRules(Validator validator) {
        validator.notBlank(this::getId, "Id")
                .notNull(this::getChild, "Child")
                .validField(this::getChild);
    }

    private final String id;
    private final TestObjectChild child;
    private TestObjectParent parent;
}
