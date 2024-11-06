package store.domain.product;

public class Product {
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
}
