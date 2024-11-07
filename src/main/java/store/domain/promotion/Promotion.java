package store.domain.promotion;

import java.time.LocalDate;

public class Promotion {
    private static final String PROMOTION_NULL = "null";
    private static final String PROMOTION_EMPTY = "";

    private String name;
    private int productPurchaseCount;
    private int productGiftCount;
    private LocalDate startDate;
    private LocalDate endDate;

    private Promotion(String name, int productPurchaseCount, int productGiftCount, LocalDate startDate,
                      LocalDate endDate) {
        this.name = name;
        this.productPurchaseCount = productPurchaseCount;
        this.productGiftCount = productGiftCount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Promotion of(String name, int productPurchaseCount, int productGiftCount, LocalDate startDate,
                               LocalDate endDate) {
        return new Promotion(name, productPurchaseCount, productGiftCount, startDate, endDate);
    }

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }

    public void checkIsNullPromotion() {
        if (name.equals(PROMOTION_NULL)) {
            name = PROMOTION_EMPTY;
        }
    }

    public boolean isPromotion() {
        return !name.equals(PROMOTION_EMPTY);
    }

    public String getName() {
        return name;
    }
}
