package store.global.config;

import java.time.LocalDate;
import java.util.List;
import store.domain.promotion.Promotion;
import store.domain.promotion.Promotions;

public class PromotionFactory extends ReadFile {
    private static final String PROMOTIONS_FILE_PATH = "src/main/resources/promotions.md";

    private Promotion createPromotion(String name, int productPurchaseCount, int productGiftCount, LocalDate startDate, LocalDate endDate) {
        return Promotion.of(name, productPurchaseCount, productGiftCount, startDate, endDate);
    }

    public Promotions createPromotions() {
        List<Promotion> promotions = read(PROMOTIONS_FILE_PATH);
        return Promotions.from(promotions);
    }

    @Override
    protected Promotion createInstance(String[] fields) {
        String name = fields[0];
        int productPurchaseCount = Integer.parseInt(fields[1]);
        int productGiftCount = Integer.parseInt(fields[2]);
        LocalDate startDate = LocalDate.parse(fields[3]);
        LocalDate endDate = LocalDate.parse(fields[4]);

        return createPromotion(name, productPurchaseCount, productGiftCount, startDate, endDate);
    }

}
