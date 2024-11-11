package store.domain.product;

import store.domain.product.dto.GetProductDto;
import store.domain.promotion.Promotion;

public class Product {
    private String name;
    private int price;
    private int quantity;
    private Promotion promotion;

    private Product(final String name, final int price, final int quantity, final Promotion promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public static Product of(final String name, final int price, final int quantity, final Promotion promotion) {
        return new Product(name, price, quantity, promotion);
    }

    public GetProductDto GetProductDto() {
        promotion.checkIsNullPromotion();
        return new GetProductDto(name, price, quantity, promotion.getName());
    }

    public boolean isSameName(final String name) {
        return this.name.equals(name);
    }

    public boolean isNullPromotion() {
        return promotion.isNullPromotion();
    }

    public boolean hasPromotion() {
        return promotion.hasPromotion();
    }

    public boolean isValidPromotionDate() {
        return promotion.isValidPromotionDate();
    }

    public int countMaxPromotionProduct(final int promotionCount) {
        return (quantity / promotionCount) * promotionCount;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public int reduceQuantity(final int count) {
        quantity -= count;
        if (quantity < 0) {
            int shortage = quantity;
            quantity = 0;
            return shortage;
        }
        return quantity;
    }

    public boolean isExhaustion() {
        return quantity == 0;
    }

    public String getName() {
        return name;
    }
}
