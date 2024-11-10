package store.domain.receipt;

import store.domain.promotion.Promotion;
import store.domain.receipt.dto.GetPromotionProductDto;

public class PromotionProduct {
    private String name;
    private int count;
    private int price;
    private Promotion promotion;

    private PromotionProduct(final String name, final int count, final int price, final Promotion promotion) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.promotion = promotion;
    }

    public static PromotionProduct of(final String name, final int count, final int price, final Promotion promotion) {
        return new PromotionProduct(name, count, price, promotion);
    }

    public String getName() {
        return name;
    }

    public int getPrices() {
        return price * count;
    }

    public int getTotalPrices() {
        return promotion.getPromotionCount() * price * count;
    }

    public GetPromotionProductDto getPromotionProductDto() {
        return new GetPromotionProductDto(name, count, price, promotion.getPromotionCount());
    }

    public boolean hasCount() {
        return count > 0;
    }
}
