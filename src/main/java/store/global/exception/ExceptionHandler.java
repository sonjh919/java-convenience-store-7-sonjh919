package store.global.exception;

import static store.global.exception.ExceptionMessage.INVALID_FILE;

import java.util.function.Function;
import store.view.OutputView;

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
}
