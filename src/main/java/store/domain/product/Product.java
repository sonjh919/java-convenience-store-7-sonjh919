package store.domain.product;

import store.domain.product.dto.GetProductDto;

public class Product {
    private static final String PROMOTION_NULL = "null";
    private static final String PROMOTION_EMPTY = "";
    private static final String NONE_QUANTITY = "재고 없음";

    private String name;
    private int price;
    private int quantity;
    private String promotion;

    private Product(String name, int price, int quantity, String promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public static Product of(String name, int price, int quantity, String promotion) {
        return new Product(name, price, quantity, promotion);
    }

    public GetProductDto GetProductDto() {
        setPromotionOutput();
        return new GetProductDto(name, price, quantity, promotion);
    }

    private void setPromotionOutput() {
        validateIsNullPromotion();
        validateIsZeroQuantity();
    }

    private void validateIsNullPromotion() {
        if (promotion.equals(PROMOTION_NULL)) {
            promotion = PROMOTION_EMPTY;
        }
    }

    private void validateIsZeroQuantity() {
        if (quantity == 0) {
            promotion = NONE_QUANTITY;
        }
    }

}
