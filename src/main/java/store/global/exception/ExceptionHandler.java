package store.global.exception;

import static store.global.exception.ExceptionMessage.INVALID_FILE;

import java.util.function.Function;
import java.util.function.Supplier;
import store.view.output.OutputView;

public class ExceptionHandler {

    @FunctionalInterface
    public interface CheckIoException<T, R> {
        R apply(T t) throws Exception;
    }

    public static <T, R> Function<T, R> IOExceptionHandler(CheckIoException<T, R> checkIoException) {
        return t -> {
            try {
                return checkIoException.apply(t);
            } catch (Exception e) {
                OutputView.printError(INVALID_FILE.message);
                throw new RuntimeException(e);
            }
        };
    }

    public static <T> T getValidInput(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
