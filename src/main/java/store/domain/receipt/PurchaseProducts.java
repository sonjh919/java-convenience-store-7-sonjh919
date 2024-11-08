package store.domain.receipt;

import static store.global.exception.ExceptionMessage.INVALID_PURCHASE_FORMAT;
import static store.global.exception.Validator.validateIsInteger;
import static store.global.exception.Validator.validateIsPositive;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PurchaseProducts {
    private static final String PRODUCT_NAME_REGEX = "([\\w가-힣]+)";
    private static final String PRODUCT_REGEX = "\\[" + PRODUCT_NAME_REGEX + "-(\\d+)\\]";
    private static final Pattern PRODUCT_LIST_PATTERN = Pattern.compile(
            "^(" + PRODUCT_REGEX + ")(," + PRODUCT_REGEX + ")*$");

    private List<PurchaseProduct> purchaseProducts;

    private PurchaseProducts(String products) {
        validateProductFormat(products);
        this.purchaseProducts = extractProducts(products);
    }

    public static PurchaseProducts from(String purchaseProducts) {
        return new PurchaseProducts(purchaseProducts);
    }

    private void validateProductFormat(String products) {
        Matcher matcher = PRODUCT_LIST_PATTERN.matcher(products);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(INVALID_PURCHASE_FORMAT.message);
        }
    }

    private List<PurchaseProduct> extractProducts(String products) {
        Matcher matcher = Pattern.compile(PRODUCT_REGEX).matcher(products);
        List<PurchaseProduct> purchaseProducts = new ArrayList<>();

        while (matcher.find()) {
            purchaseProducts.add(extractProduct(matcher));
        }

        return purchaseProducts;
    }

    private PurchaseProduct extractProduct(Matcher matcher) {
        String name = matcher.group(1);
        int count = validateIsInteger((matcher.group(2)));
        validateIsPositive(count);

        return PurchaseProduct.of(name, count);
    }

    public void isAvailableProducts() {
        purchaseProducts.forEach(PurchaseProduct::validateExistProducts);
    }

    public List<PurchaseProduct> getPurchaseProducts() {
        return purchaseProducts;
    }
}
