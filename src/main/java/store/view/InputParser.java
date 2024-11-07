package store.view;

import static store.global.exception.ExceptionMessage.INVALID_PURCHASE_FORMAT;
import static store.global.exception.Validator.validateIsInteger;
import static store.global.exception.Validator.validateIsPositive;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import store.view.dto.GetPurchaseProductDto;
import store.view.dto.GetPurchaseProductsDto;

public class InputParser {
    private static final String PRODUCT_NAME_REGEX = "([\\w가-힣]+)";
    private static final String PRODUCT_REGEX = "\\[" + PRODUCT_NAME_REGEX + "-(\\d)+\\]";
    private static final Pattern PRODUCT_LIST_PATTERN = Pattern.compile("^(" + PRODUCT_REGEX + ")(," + PRODUCT_REGEX + ")*$");

    public GetPurchaseProductsDto purchase(String products) {
        validateProductFormat(products);
        return extractProducts(products);
    }

    private void validateProductFormat(String products) {
        Matcher matcher = PRODUCT_LIST_PATTERN.matcher(products);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(INVALID_PURCHASE_FORMAT.message);
        }
    }

    private GetPurchaseProductsDto extractProducts(String products) {
        Matcher matcher = Pattern.compile(PRODUCT_REGEX).matcher(products);
        List<GetPurchaseProductDto> purchaseProducts = new ArrayList<>();

        while (matcher.find()) {
            purchaseProducts.add(extractProduct(matcher));
        }

        return new GetPurchaseProductsDto(purchaseProducts);
    }

    private GetPurchaseProductDto extractProduct(Matcher matcher) {
        String name = matcher.group(1);
        int count = validateIsInteger((matcher.group(2)));
        validateIsPositive(count);

        return new GetPurchaseProductDto(name, count);
    }
}
