package store.domain.product;

import static store.global.exception.ExceptionMessage.NON_EXISTENT_PRODUCT;

import java.util.ArrayList;
import java.util.List;
import store.domain.product.dto.GetProductsDto;
import store.domain.promotion.Promotion;
import store.domain.receipt.PurchaseProduct;

public enum Products {
    INSTANCE;

    private final List<Product> products = new ArrayList<>();

    public Products from(List<Product> products) {
        this.products.clear();
        this.products.addAll(products);
        return this;
    }

    public GetProductsDto getProducts() {
        return new GetProductsDto(products.stream()
                .map(Product::GetProductDto)
                .toList());
    }

    public void validateExistProducts(PurchaseProduct purchaseProduct) {
        validateIsAvailableProduct(purchaseProduct);
        validateCanPurchaseProduct(purchaseProduct);
    }

    private void validateIsAvailableProduct(PurchaseProduct purchaseProduct) {
        findProductsByName(purchaseProduct).stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NON_EXISTENT_PRODUCT.message));
    }

    private void validateCanPurchaseProduct(PurchaseProduct purchaseProduct) {
        if (getTotalQuantity(purchaseProduct) < purchaseProduct.getCount()) {
            throw new IllegalArgumentException(NON_EXISTENT_PRODUCT.message);
        }
    }

    private int getTotalQuantity(PurchaseProduct purchaseProduct) {
        return findProductsByName(purchaseProduct).stream()
                .mapToInt(Product::getQuantity)
                .sum();
    }

    private List<Product> findProductsByName(PurchaseProduct purchaseProduct) {
        return products.stream()
                .filter(product -> product.isSameName(purchaseProduct.getName()))
                .toList();
    }

    private Product findNullPromotionProductByName(PurchaseProduct purchaseProduct) {
        return findProductsByName(purchaseProduct).stream()
                .filter(Product::isNullPromotion)
                .findFirst().
                orElse(null);
    }

    private Product findPromotionProductByName(PurchaseProduct purchaseProduct) {
        return findProductsByName(purchaseProduct).stream()
                .filter(Product::hasPromotion)
                .findFirst().
                orElse(null);
    }

    public boolean canApplyPromotion(PurchaseProduct purchaseProduct) {
        Product product = findPromotionProductByName(purchaseProduct);
        if (product == null) {
            return false;
        }
        ;
        return product.isValidPromotionDate();
    }

    public int calculateShortageProduct(PurchaseProduct purchaseProduct) {
        int promotionCount = getPromotionCount(purchaseProduct);
        Product product = findPromotionProductByName(purchaseProduct);
        return product.calculatePromotionCount(promotionCount, purchaseProduct.getCount());
    }

    private int getPromotionCount(PurchaseProduct purchaseProduct) {
        Promotion promotion = findPromotionByProduct(purchaseProduct);
        return promotion.getPromotionCount();
    }

    private Promotion findPromotionByProduct(PurchaseProduct purchaseProduct) {
        Product product = findPromotionProductByName(purchaseProduct);
        return product.getPromotion();
    }

    public int countPromotionProduct(PurchaseProduct purchaseProduct) {
        int promotionCount = getPromotionCount(purchaseProduct);
        Product product = findPromotionProductByName(purchaseProduct);
        return product.countPromotionProduct(promotionCount);
    }

    public int getPriceByName(String name) {
        return products.stream()
                .filter(product -> product.isSameName(name))
                .findFirst()
                .map(Product::getPrice)
                .orElseThrow(() -> new IllegalArgumentException(NON_EXISTENT_PRODUCT.message));
    }

}
