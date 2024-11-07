package store.controller;

import store.domain.product.Products;
import store.domain.promotion.Promotions;
import store.view.View;

public class StoreController {
    private final View view;
    private final Products products;
    private final Promotions promotions;

    public StoreController(final View view, final Products products, final Promotions promotions) {
        this.view = view;
        this.products = products;
        this.promotions = promotions;
    }

    public void start() {
        showProducts();


    }

    private void showProducts() {
        view.outputProducts(products.getProducts());
    }

}
