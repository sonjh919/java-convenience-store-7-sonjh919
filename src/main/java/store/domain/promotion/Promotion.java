package store.domain.promotion;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Promotion {
    private static final String PROMOTION_NULL = "null";
    private static final String PROMOTION_EMPTY = "";

    private String name;
    private int productPurchaseCount;
    private int productGiftCount;
    private LocalDate startDate;
    private LocalDate endDate;

    private Promotion(final String name, final int productPurchaseCount, final int productGiftCount,
                      final LocalDate startDate,
                      final LocalDate endDate) {
        this.name = name;
        this.productPurchaseCount = productPurchaseCount;
        this.productGiftCount = productGiftCount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Promotion of(final String name, final int productPurchaseCount, final int productGiftCount,
                               final LocalDate startDate,
                               final LocalDate endDate) {
        return new Promotion(name, productPurchaseCount, productGiftCount, startDate, endDate);
    }

    public boolean isSameName(final String name) {
        return this.name.equals(name);
    }

    public void checkIsNullPromotion() {
        if (name.equals(PROMOTION_NULL)) {
            name = PROMOTION_EMPTY;
        }
    }

    public boolean isNullPromotion() {
        return name.equals(PROMOTION_EMPTY);
    }

    public boolean hasPromotion() {
        return !name.equals(PROMOTION_EMPTY);
    }

    public String getName() {
        return name;
    }

    public boolean isValidPromotionDate() {
        LocalDateTime currentDateTime = DateTimes.now();
        return currentDateTime.isAfter(startDate.atStartOfDay()) && currentDateTime.isBefore(
                endDate.atTime(23, 59, 59));
    }

    public int getPromotionCount() {
        return productPurchaseCount + productGiftCount;
    }
}
