package store.global.exception;

public enum ExceptionMessage {
    INVALID_FILE("유효하지 않은 파일 형식입니다.");

    private static final String PREFIX = "[ERROR] ";

    final String message;

    ExceptionMessage(final String message) {
        this.message = PREFIX + message;
    }
}
