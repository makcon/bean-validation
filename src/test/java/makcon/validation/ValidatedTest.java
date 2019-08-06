package makcon.validation;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ValidatedTest {

    @Test @Ignore
    public void validate_idBlankChildNull_2errors1() throws Exception {
        TestObjectChild child = new TestObjectChild(null, null);
        TestObjectParent object = new TestObjectParent("  ", null);
        TestObjectParent object2 = new TestObjectParent("", child);
        object.setParent(object2);

        List<TestObjectParent> asList = Arrays.asList(object, object2);

        List<Validator> validators = asList.stream()
                .map(it -> Validator.create()
                        .notBlank(it::getId, "Id list")
                        .notNull(it::getChild, "Child list")
                )
                .collect(toList());

//        asList.stream()
//                .map(it -> validator -> validator)

        Validator childValidator = Validator.create()
                .notNull(child::getName, "Name of child");
        Validator object2Validator = Validator.create()
                .notNull(object2::getParent, "Object2 parent")
                .notEmpty(object2::getId, "Id of object2")
                .validField(childValidator);

        List<String> errors = Validator.create()
                .notBlank(object::getId, "Id of object")
                .validField(object2Validator)
                .validFieldCollection(validators)
//                .notNull(() -> object.getChild().getName(), "Name")
                .validate();

        assertEquals(errors.toString(), 2, errors.size());
        assertTrue(errors.toString(), errors.contains("'Id' can not be null, empty or blank"));
        assertTrue(errors.toString(), errors.contains("'Child' can not be null"));
    }

    @Test
    public void validate_idBlankChildNull_2errors() throws Exception {
        TestObjectParent object = new TestObjectParent("  ", null);

        List<String> errors = object.validate();

        assertEquals(errors.toString(), 2, errors.size());
        assertTrue(errors.toString(), errors.contains("'Id' can not be null, empty or blank"));
        assertTrue(errors.toString(), errors.contains("'Child' can not be null"));
    }

    @Test
    public void validate_idNullChildSetNull_2errors() throws Exception {
        TestObjectParent object = new TestObjectParent(null, new TestObjectChild("name", emptySet()));

        List<String> errors = object.validate();

        assertEquals(errors.toString(), 2, errors.size());
        assertTrue(errors.toString(), errors.contains("'Id' can not be null, empty or blank"));
        assertTrue(errors.toString(), errors.contains("'Sets' can not be null or empty"));
    }

    @Test
    public void validate_allValid_noErrors() throws Exception {
        TestObjectParent object = new TestObjectParent("id", new TestObjectChild("name", singleton("val")));

        List<String> errors = object.validate();

        assertTrue(errors.toString(), errors.isEmpty());
    }
}