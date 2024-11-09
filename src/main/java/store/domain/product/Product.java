package store.domain.product;

import store.domain.product.dto.GetProductDto;
import store.domain.promotion.Promotion;

public class Product {
    private String name;
    private int price;
    private int quantity;
    private Promotion promotion;

    private Product(String name, int price, int quantity, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public static Product of(String name, int price, int quantity, Promotion promotion) {
        return new Product(name, price, quantity, promotion);
    }

    public GetProductDto GetProductDto() {
        promotion.checkIsNullPromotion();
        return new GetProductDto(name, price, quantity, promotion.getName());
    }

    public boolean isSameName(String name) {
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

    public int countPromotionProduct(int promotionCount) {
        return (quantity / promotionCount)*promotionCount;
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
}
