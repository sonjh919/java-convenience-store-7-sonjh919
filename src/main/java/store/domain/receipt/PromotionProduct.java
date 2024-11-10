package store.domain.receipt;

import store.domain.promotion.Promotion;
import store.domain.receipt.dto.GetPromotionProductDto;

public class PromotionProduct {
    private String name;
    private int count;
    private int price;
    private Promotion promotion;

    private PromotionProduct(String name, int count, int price, Promotion promotion) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.promotion = promotion;
    }

    public static PromotionProduct of(String name, int count, int price, Promotion promotion) {
        return new PromotionProduct(name, count, price, promotion);
    }

    public String getName() {
        return name;
    }

    public int getPrices() {
        return price * count;
    }

    public int getTotalPrices() {
        return promotion.getPromotionCount() * price;
    }

    public GetPromotionProductDto getPromotionProductDto() {
        return new GetPromotionProductDto(name, count, price, promotion.getPromotionCount());
    }
}
