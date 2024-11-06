package store.global.config;

import java.util.List;
import store.domain.Product;
import store.domain.Products;

public class ProductFactory extends ReadFile {
    private static final String PRODUCTS_FILE_PATH = "src/main/resources/products.md";

    private Product createProduct(String name, int price, int quantity, String promotion) {
        return new Product(name, price, quantity, promotion);
    }

    public Products createProducts() {
        List<Product> products = read(PRODUCTS_FILE_PATH);
        return new Products(products);
    }

    @Override
    protected Product createInstance(String[] fields) {
        String name = fields[0];
        int price = Integer.parseInt(fields[1]);
        int quantity = Integer.parseInt(fields[2]);
        String promotion = fields[3];

        return createProduct(name, price, quantity, promotion);
    }
}
