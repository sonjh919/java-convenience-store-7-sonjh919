package store.view.output;

public enum Output {
    WELCOME("안녕하세요. W편의점입니다."),
    PRODUCT_LIST("현재 보유하고 있는 상품입니다."),
    PRODUCT_SEPARATOR("- "),
    PRODUCT("%s %,d원 %d개 %s"),
    PRODUCT_NO_QUANTITY("%s %,d원 재고 없음"),

    PURCHASE("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"),

    PROMOTION_SHORTAGE("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)"),

    NEW_LINE(System.lineSeparator());

    final String message;

    Output(final String message) {
        this.message = message;
    }

}