package store.global.exception;

public enum ExceptionMessage {
    INVALID_FILE("유효하지 않은 파일 형식입니다."),
    INVALID_PROMOTION("해당 프로모션을 찾을 수 없습니다."),
    DUPLICATE_PROMOTION("동일 상품에 여러 프로모션을 적용할 수 없습니다."),
    INVALID_PURCHASE_FORMAT("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    NON_EXISTENT_PRODUCT("존재하지 않는 상품입니다. 다시 입력해 주세요."),
    EXCEED_PRODUCT_COUNT("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    INVALID_INPUT("잘못된 입력입니다. 다시 입력해 주세요."),
    INVALID_YES_OR_NO("Y 또는 N으로 다시 입력해 주세요.");

    private static final String PREFIX = "[ERROR] ";

    public final String message;

    ExceptionMessage(final String message) {
        this.message = PREFIX + message;
    }
}
