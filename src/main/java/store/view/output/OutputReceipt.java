package store.view.output;

public enum OutputReceipt {
    NEW_LINE(System.lineSeparator()),

    START_RECEIPT("==============W 편의점================"),
    PURCHASE_HEADER(String.format("%-8s\t%10s\t%8s", "상품명", "수량", "금액")),
    PURCHASE_PRODUCT("%-15s\t%-,10d%-,10d"),

    PROMOTION_HEADER("=============증     정==============="),
    PROMOTION_PRODUCT("%-15s\t%-,10d"),

    AMOUNT_HEADER("===================================="),
    TOTAL_AMOUNT("총구매액   \t\t\t%-,10d%-,10d" + NEW_LINE.message),
    PROMOTION_DISCOUNT_AMOUNT("행사할인   \t\t\t%,16d" + NEW_LINE.message),
    MEMBERSHIP_DISCOUNT_AMOUNT("멤버십할인  \t\t\t%,16d" + NEW_LINE.message),
    MEMBERSHIP_DISCOUNT_ZERO("멤버십할인  \t\t\t\t\t\t  -0" + NEW_LINE.message),
    PAY_AMOUNT("내실돈    \t\t\t%,16d" + NEW_LINE.message);

    final String message;

    OutputReceipt(final String message) {
        this.message = message;
    }
}
