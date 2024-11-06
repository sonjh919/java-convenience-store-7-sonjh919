package store.domain.promotion;

import java.time.LocalDate;

public class Promotion {
    private String name;
    private int productPurchaseCount;
    private int productGiftCount;
    private LocalDate startDate;
    private LocalDate endDate;

    private Promotion(String name, int productPurchaseCount, int productGiftCount, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.productPurchaseCount = productPurchaseCount;
        this.productGiftCount = productGiftCount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Promotion of(String name, int productPurchaseCount, int productGiftCount, LocalDate startDate, LocalDate endDate) {
        return new Promotion(name, productPurchaseCount, productGiftCount, startDate, endDate);
    }
}
