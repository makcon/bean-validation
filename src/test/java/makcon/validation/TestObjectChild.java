package makcon.validation;

import java.util.Set;

public class TestObjectChild implements Validated {

    @Override
    public void defineRules(Validator validator) {
        validator.notNull(this::getName, "Name")
                .notEmpty(this::getSets, "Sets");
    }

    private String name;
    private Set<String> sets;

    public TestObjectChild(String name,
                           Set<String> sets) {
        this.name = name;
        this.sets = sets;
    }

    public String getName() {
        return name;
    }

    public Set<String> getSets() {
        return sets;
    }
}
