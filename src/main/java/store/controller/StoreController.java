package store.controller;

import static store.global.exception.ExceptionHandler.getValidInput;

import store.domain.product.Products;
import store.domain.promotion.Promotions;
import store.view.View;
import store.view.dto.GetPurchaseProductsDto;

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
        getValidInput(this::purchase);
    }

    private void showProducts() {
        view.outputProducts(products.getProducts());
    }

    private GetPurchaseProductsDto purchase() {
        GetPurchaseProductsDto getPurchaseProductsDto = view.purchase();
        products.isAvailableProducts(getPurchaseProductsDto);

        return getPurchaseProductsDto;
    }
}
