package makcon.validation.message;

public final class Message {
    
    public enum Pattern {

        NULL("'%s' can not be null"),
        EMPTY("'%s' can not be null or empty"),
        BLANK("'%s' can not be null, empty or blank"),
        ELEMENT_NULL("Element in '%s' can not be null"),
        ELEMENT_EMPTY("Element in '%s' can not be null or empty"),
        ELEMENT_BLANK("Element in '%s' can not be null, empty or blank"),
        DATE_FROM_GREATER("'%s' to must be greater or equals than '%s' from"),
        MIN_GREATER("'%s' must be greater or equals than '%s'"),
        MAX("'%s' must be equals or less than %d"),
        MIN("'%s' must be equals or more than %d"),
        ;

        private final String value;

        Pattern(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private Message() {
    }

    public static String get(Pattern pattern, Object... params) {
        return String.format(pattern.getValue(), params);
    }
}