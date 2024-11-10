package store.global.exception;

public class Validator {
    public static int validateIsInteger(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.message);
        }
    }

    public static void validateIsPositive(final int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.message);
        }
    }
}
