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
        do {
            showProducts();
            PurchaseProducts purchaseProducts = getValidInput(this::purchase);

            applyPromotionDiscount(purchaseProducts);
            applyMembershipDiscount();

            printReceipt();
            manageProducts(purchaseProducts);
        } while (getValidInput(view::inputContinue).isYes());
    }

    private void showProducts() {
        receipt.clear();
        view.outputProducts(products.getProducts());
    }

    private PurchaseProducts purchase() {
        return PurchaseProducts.from(view.purchase());
    }

    private void applyPromotionDiscount(final PurchaseProducts purchaseProducts) {
        purchaseProducts.getPurchaseProducts().forEach(purchaseProduct -> {
            if (products.canApplyPromotion(purchaseProduct)) {
                checkPromotionProductShortage(purchaseProduct);
                checkPromotionQuantityRequirement(purchaseProduct);
                addPRomotionProduct(purchaseProduct);
            }
            receipt.addPurchaseProduct(purchaseProduct);
        });
    }

    private void addPRomotionProduct(PurchaseProduct purchaseProduct) {
        receipt.addPromotionProduct(
                PromotionProduct.of(purchaseProduct.getName(), products.countPromotionProduct(purchaseProduct),
                        products.getPriceByName(purchaseProduct.getName()),
                        products.getPromotionByName(purchaseProduct)));
    }

    private void applyMembershipDiscount() {
        if (IsApplyMembershipDiscount()) {
            receipt.getMembershipDiscount();
        }
    }

    private boolean IsApplyMembershipDiscount() {
        return (getValidInput(view::inputMembershipDiscount)).isYes();
    }

    private void checkPromotionProductShortage(final PurchaseProduct purchaseProduct) {
        int shortage = products.calculateShortageProduct(purchaseProduct);
        if (shortage > 0) {
            if (reduceCountForShortageProduct(purchaseProduct.getName(), shortage)) {
                purchaseProduct.reduceCount(shortage);
            }
        }
    }

    private void checkPromotionQuantityRequirement(final PurchaseProduct purchaseProduct) {
        if (products.isPromotionQuantityRequirement(purchaseProduct)) {
            if (shouldAddPromotionProduct(purchaseProduct.getName())) {
                purchaseProduct.increaseCount();
            }
        }
    }

    private boolean reduceCountForShortageProduct(final String name, final int shortage) {
        return (getValidInput(() -> view.inputShortageProduct(name, shortage))).isNo();
    }

    private boolean shouldAddPromotionProduct(final String name) {
        return (getValidInput(() -> view.inputAddPromotionProduct(name))).isYes();
    }

    private void printReceipt() {
        view.outputReceipt(receipt.getReceipt());
    }

    private void manageProducts(final PurchaseProducts purchaseProducts) {
        purchaseProducts.getPurchaseProducts().forEach(products::reduceProductQuantity);
    }
}
