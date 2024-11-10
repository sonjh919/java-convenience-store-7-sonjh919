package store.domain.receipt;

import store.domain.product.Products;
import store.domain.receipt.dto.GetPurchaseProductDto;

public class PurchaseProduct {
    private String name;
    private int count;
    private int price;

    private PurchaseProduct(String name, int count, int price) {
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public static PurchaseProduct of(String name, int count) {
        return new PurchaseProduct(name, count, Products.INSTANCE.getPriceByName(name));
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void validateIsExistProducts() {
        Products.INSTANCE.validateExistProducts(this);
    }

    public void reduceCount(int shortage) {
        this.count = count - shortage;
    }

    public void increaseCount() {
        this.count++;
    }

    public int getPrices() {
        return price * count;
    }

    public GetPurchaseProductDto getPurchaseProductDto() {
        return new GetPurchaseProductDto(name, count, price);
    }
}
