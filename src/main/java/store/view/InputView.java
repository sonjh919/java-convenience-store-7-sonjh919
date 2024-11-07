package store.view;

import static store.global.exception.Validator.validateIsInteger;
import static store.global.exception.Validator.validateIsPositive;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import store.view.dto.GetPurchaseProductDto;
import store.view.dto.GetPurchaseProductsDto;

public class InputView {
    private static final String PRODUCT_NAME_REGEX = "([\\w가-힣]+)";
    private static final String PRODUCT_REGEX = "\\[" + PRODUCT_NAME_REGEX + "-(\\d)+\\]";
    private static final Pattern PRODUCT_LIST_PATTERN = Pattern.compile("^(" + PRODUCT_REGEX + ")(," + PRODUCT_REGEX + ")*$");

    public GetPurchaseProductsDto purchase() {
        String products = Console.readLine();
        Matcher matcher = PRODUCT_LIST_PATTERN.matcher(products);

        validateProductFormat(matcher);
        return extractProducts(products);
    }

    private void validateProductFormat(Matcher matcher) {
        if (!matcher.matches()) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
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
