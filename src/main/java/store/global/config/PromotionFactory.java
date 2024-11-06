package store.global.config;

import java.time.LocalDate;
import java.util.List;
import store.domain.Promotion;
import store.domain.Promotions;

public class PromotionFactory extends ReadFile {
    private static final String PROMOTIONS_FILE_PATH = "src/main/resources/promotions.md";

    private Promotion createPromotion(String name, int buy, int get, LocalDate startDate, LocalDate endDate) {
        return new Promotion(name, buy, get, startDate, endDate);
    }

    public Promotions createPromotions() {
        List<Promotion> promotions = read(PROMOTIONS_FILE_PATH);
        return new Promotions(promotions);
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
