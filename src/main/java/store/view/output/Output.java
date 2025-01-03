package store.view.output;

public enum Output {
    WELCOME("안녕하세요. W편의점입니다."),
    PRODUCT_LIST("현재 보유하고 있는 상품입니다."),
    PRODUCT_SEPARATOR("- "),
    PRODUCT("%s %,d원 %d개 %s"),
    PRODUCT_NO_QUANTITY("%s %,d원 재고 없음 %s"),

    PURCHASE("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"),

    PROMOTION_SHORTAGE("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)"),
    ADD_PROMOTION_PRODUCT_COUNT("현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)"),

    MEMBERSHIP_DISCOUNT("멤버십 할인을 받으시겠습니까? (Y/N)"),

    CONTINUE("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)"),

    NEW_LINE(System.lineSeparator());

    final String message;

    Output(final String message) {
        this.message = message;
    }

}
