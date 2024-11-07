package store.view;

public enum Output {
    WELCOME("안녕하세요. W편의점입니다."),
    PRODUCT_LIST("현재 보유하고 있는 상품입니다."),
    PRODUCT_SEPARATOR("- "),
    PRODUCT("%s %,d원 %d개 %s"),
    PRODUCT_NO_QUANTITY("%s %,d원 재고 없음"),

    NEW_LINE(System.lineSeparator());

    final String message;

    Output(final String message) {
        this.message = message;
    }

}
