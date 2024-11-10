package store.global.exception;

public class Validator {
    public static int validateIsInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.message);
        }
    }

    public static void validateIsPositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.message);
        }
    }
}
