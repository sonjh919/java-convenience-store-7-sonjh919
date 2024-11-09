package store.controller;

import static store.global.exception.ExceptionHandler.getValidInput;

import store.domain.product.Products;
import store.domain.promotion.Promotions;
import store.domain.receipt.PromotionProduct;
import store.domain.receipt.PurchaseProduct;
import store.domain.receipt.PurchaseProducts;
import store.domain.receipt.Receipt;
import store.view.View;

public class StoreController {
    private final View view;
    private final Products products;
    private final Promotions promotions;
    private final Receipt receipt;

    public StoreController(final View view, final Promotions promotions, final Products products) {
        this.view = view;
        this.promotions = promotions;
        this.products = products;
        this.receipt = new Receipt();
    }

    public void start() {
        showProducts();
        PurchaseProducts purchaseProducts = getValidInput(this::purchase);

        applyPromotions(purchaseProducts);
        receipt.print();
    }

    private void showProducts() {
        view.outputProducts(products.getProducts());
    }

    private PurchaseProducts purchase() {
        PurchaseProducts purchaseProducts = PurchaseProducts.from(view.purchase());
        purchaseProducts.isAvailableProducts();

        return purchaseProducts;
    }

    private void applyPromotions(PurchaseProducts purchaseProducts) {
        purchaseProducts.getPurchaseProducts().forEach(purchaseProduct -> {
            if (products.canApplyPromotion(purchaseProduct)) {
                checkPromotionProductShortage(purchaseProduct);
                checkPromotionQuantityRequirement(purchaseProduct);
            }
            receipt.addPurchaseProduct(purchaseProduct);
            receipt.addPromotionProduct(
                    PromotionProduct.of(purchaseProduct.getName(), products.countPromotionProduct(purchaseProduct)));
        });
    }

    private void checkPromotionProductShortage(PurchaseProduct purchaseProduct) {
        int shortage = products.calculateShortageProduct(purchaseProduct);
        if (shortage > 0) {
            if (reduceCountForShortageProduct(purchaseProduct.getName(), shortage)) {
                purchaseProduct.reduceCount(shortage);
            }
        }
    }

    private void checkPromotionQuantityRequirement(PurchaseProduct purchaseProduct) {
        if (products.isPromotionQuantityRequirement(purchaseProduct)) {
            if(addPromotionProduct(purchaseProduct.getName())) {
                purchaseProduct.increaseCount();
            }
        }
    }

    private boolean reduceCountForShortageProduct(String name, int shortage) {
        return (getValidInput(() -> view.inputShortageProduct(name, shortage))).isNo();
    }

    private boolean addPromotionProduct(String name) {
        return (getValidInput(() -> view.inputAddPromotionProduct(name))).isYes();
    }
}
