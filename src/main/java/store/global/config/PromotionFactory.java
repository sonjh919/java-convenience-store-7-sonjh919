package store.global.config;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import store.domain.promotion.Promotion;
import store.domain.promotion.Promotions;

public class PromotionFactory extends ReadFile {
    private static final String PROMOTIONS_FILE_PATH = "src/main/resources/promotions.md";

    private Promotion createPromotion(final String name, final int productPurchaseCount, final int productGiftCount,
                                      final LocalDate startDate, final LocalDate endDate) {
        return Promotion.of(name, productPurchaseCount, productGiftCount, startDate, endDate);
    }

    public Promotions createPromotions() {
        List<Promotion> promotions = new ArrayList<>(read(PROMOTIONS_FILE_PATH));
        promotions.add(Promotion.of("null", 0, 0, LocalDate.now(), LocalDate.now()));
        return Promotions.INSTANCE.from(promotions);
    }

    @Override
    protected Promotion createInstance(final String[] fields) {
        String name = fields[0];
        int productPurchaseCount = Integer.parseInt(fields[1]);
        int productGiftCount = Integer.parseInt(fields[2]);
        LocalDate startDate = LocalDate.parse(fields[3]);
        LocalDate endDate = LocalDate.parse(fields[4]);

        return createPromotion(name, productPurchaseCount, productGiftCount, startDate, endDate);
    }

}
