package store.domain.promotion;

import static store.global.exception.ExceptionMessage.INVALID_PROMOTION;

import java.util.ArrayList;
import java.util.List;

public enum Promotions {
    INSTANCE;


    private final List<Promotion> promotions = new ArrayList<>();

    public Promotions from(List<Promotion> promotions) {
        this.promotions.clear();
        this.promotions.addAll(promotions);
        return this;
    }

    public Promotion getPromotionByName(String inputPromotion) {
        return promotions.stream()
                .filter(promotion -> promotion.isSameName(inputPromotion))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PROMOTION.message));
    }
}
