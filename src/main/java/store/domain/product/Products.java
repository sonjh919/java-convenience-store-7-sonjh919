package store.domain.product;

import static store.global.exception.ExceptionMessage.EXCEED_PRODUCT_COUNT;
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
            throw new IllegalArgumentException(EXCEED_PRODUCT_COUNT.message);
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
        return product.isValidPromotionDate();
    }

    private int getPromotionCount(PurchaseProduct purchaseProduct) {
        Promotion promotion = findPromotionByProduct(purchaseProduct);
        return promotion.getPromotionCount();
    }

    private Promotion findPromotionByProduct(PurchaseProduct purchaseProduct) {
        Product product = findPromotionProductByName(purchaseProduct);
        return product.getPromotion();
    }

    public int calculateShortageProduct(PurchaseProduct purchaseProduct) {
        int promotionCount = getPromotionCount(purchaseProduct);
        Product product = findPromotionProductByName(purchaseProduct);
        int countPromotionProduct = product.countMaxPromotionProduct(promotionCount);
        if(countPromotionProduct == 0) {
            return 0;
        }
        return purchaseProduct.getCount() - countPromotionProduct;
    }

    public int countPromotionProduct(PurchaseProduct purchaseProduct) {
        Product product = findPromotionProductByName(purchaseProduct);
        return Math.min(product.getQuantity(), purchaseProduct.getCount())/getPromotionCount(purchaseProduct);
    }

    public boolean isPromotionQuantityRequirement(PurchaseProduct purchaseProduct) {
        Product product = findPromotionProductByName(purchaseProduct);
        int promotionCount = getPromotionCount(purchaseProduct);

        if((purchaseProduct.getCount() % promotionCount) + 1 == promotionCount) {
            return purchaseProduct.getCount() + 1 <= product.getQuantity();
        }
        return false;
    }

    public int getPriceByName(String name) {
        return products.stream()
                .filter(product -> product.isSameName(name))
                .findFirst()
                .map(Product::getPrice)
                .orElseThrow(() -> new IllegalArgumentException(NON_EXISTENT_PRODUCT.message));
    }

    public Promotion getPromotionByName(PurchaseProduct purchaseProduct) {
        Product product = findPromotionProductByName(purchaseProduct);
        return product.getPromotion();
    }

    public void reduceProductQuantity(PurchaseProduct purchaseProduct) {
        Product product = findPromotionProductByName(purchaseProduct);
        Product nullPromotionProduct = findNullPromotionProductByName(purchaseProduct);

        if(isNullOrExhausted(product)) {
            nullPromotionProduct.reduceQuantity(purchaseProduct.getCount());
            return;
        }

        reduceProductOfShortage(product, nullPromotionProduct, purchaseProduct);
    }

    private void reduceProductOfShortage(Product product, Product nullPromotionProduct, PurchaseProduct purchaseProduct) {
        int shortage = product.reduceQuantity(purchaseProduct.getCount());
        if(shortage < 0) {
            nullPromotionProduct.reduceQuantity(-shortage);
        }
    }

    private boolean isNullOrExhausted(Product product) {
        return product == null || product.isExhaustion();
    }
}
