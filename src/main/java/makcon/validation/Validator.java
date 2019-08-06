package makcon.validation;

import makcon.validation.rule.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Validator {

    private final List<Rule> rules = new ArrayList<>();
    private final List<Validator> embedded = new ArrayList<>();

    public static Validator create() {
        return new Validator();
    }

    public Validator addRule(Rule rule) {
        rules.add(rule);
        return this;
    }

    public Validator notNull(Supplier<?> supplier,
                             String fieldName) {
        return addRule(new NotNull(supplier, fieldName));
    }

    public Validator notEmpty(Supplier<?> supplier,
                              String fieldName) {
        return addRule(new NotEmpty(supplier, fieldName));
    }

    public Validator notBlank(Supplier<String> supplier,
                              String fieldName) {
        return addRule(new NotBlank(supplier, fieldName));
    }

    public Validator elementNotNull(Supplier<Collection<?>> supplier,
                                    String fieldName) {
        return addRule(new ElementNotNull(supplier, fieldName));
    }

    public Validator elementNotEmpty(Supplier<Collection<String>> supplier,
                                     String fieldName) {
        return addRule(new ElementNotEmpty(supplier, fieldName));
    }

    public Validator elementNotBlank(Supplier<Collection<String>> supplier,
                                     String fieldName) {
        return addRule(new ElementNotBlank(supplier, fieldName));
    }

    public Validator dateToGreaterOrEquals(Supplier<Instant> from,
                                           Supplier<Instant> to,
                                           String fieldName) {
        return addRule(new DateToGreaterOrEquals(from, to, fieldName));
    }

    public Validator min(Number value,
                         Supplier<Number> supplier,
                         String fieldName) {
        return addRule(new Min(value, supplier, fieldName));
    }

    public Validator max(Number value,
                         Supplier<Number> supplier,
                         String fieldName) {
        return addRule(new Max(value, supplier, fieldName));
    }

    public Validator validField(Supplier<Validated> field) {
        if (field.get() != null) {
            embedded.add(field.get().getValidator());
        }
        return this;
    }

    public Validator validField(Validator validator) {
        embedded.add(validator);
        return this;
    }

    public Validator validFieldMap(Supplier<Map<? extends Validated, ? extends Validated>> field) {
        if (field.get() != null) {
            field.get().forEach((k, v) -> {
                addField(k);
                addField(v);
            });
        }
        return this;
    }

    public Validator validFieldMap(Map<Validator, Validator> validator) {
        embedded.addAll(validator.keySet());
        embedded.addAll(validator.values());
        return this;
    }

    public Validator validFieldMapKeys(Supplier<Map<? extends Validated, ?>> field) {
        if (field.get() != null) {
            field.get().forEach((k, v) -> addField(k));
        }
        return this;
    }

    public Validator addEmbeddedMapKeys(Map<Validator, ?> validator) {
        embedded.addAll(validator.keySet());
        return this;
    }

    public Validator validFieldMapValues(Supplier<Map<?, ? extends Validated>> field) {
        if (field.get() != null) {
            field.get().forEach((k, v) -> addField(v));
        }
        return this;
    }

    public Validator addEmbeddedMapValues(Map<?, Validator> validator) {
        embedded.addAll(validator.values());
        return this;
    }

    public Validator validFieldCollection(Supplier<Collection<? extends Validated>> field) {
        if (field.get() != null) {
            field.get().forEach(this::addField);
        }
        return this;
    }

    public Validator validFieldCollection(List<Validator> validator) {
        embedded.addAll(validator);
        return this;
    }

    public List<String> validate() {
        Stream<String> errors = rules.stream()
                .filter(r -> !r.isValid())
                .map(Rule::getMessage);

        if (embedded.isEmpty()) {
            return errors.collect(toList());
        }

        Stream<String> embeddedErrors = embedded.stream()
                .flatMap(v -> v.validate().stream());

        return Stream.concat(errors, embeddedErrors)
                .collect(toList());
    }

    private void addField(Validated validated) {
        if (validated != null) {
            embedded.add(validated.getValidator());
        }
    }
}
