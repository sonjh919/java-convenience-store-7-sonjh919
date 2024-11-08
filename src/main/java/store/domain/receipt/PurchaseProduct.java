package store.domain.receipt;

import store.domain.product.Products;

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

    public int getPrice() {
        return price;
    }

    public void validateExistProducts() {
        Products.INSTANCE.validateExistProducts(this);
    }

    public void reduceCount(int shortage) {
        this.count = count - shortage;
    }
}
