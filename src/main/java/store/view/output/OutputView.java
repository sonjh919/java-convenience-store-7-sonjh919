package store.view.output;

import static store.view.output.Output.ADD_PROMOTION_PRODUCT_COUNT;
import static store.view.output.Output.NEW_LINE;
import static store.view.output.Output.PRODUCT;
import static store.view.output.Output.PRODUCT_NO_QUANTITY;
import static store.view.output.Output.PRODUCT_SEPARATOR;
import static store.view.output.Output.PROMOTION_SHORTAGE;

import store.domain.product.dto.GetProductDto;
import store.domain.product.dto.GetProductsDto;

public class OutputView {

    public static void printError(String message) {
        System.out.println(message);
    }

    public void printMessage(final Output output) {
        System.out.println(output.message);
    }

    public void printNewLine() {
        System.out.print(NEW_LINE.message);
    }

    public void printProducts(GetProductsDto productsDto) {
        printNewLine();

        productsDto.GetProductDtos().forEach(productDto -> {
            System.out.print(PRODUCT_SEPARATOR.message);
            printProduct(productDto);
            printNewLine();
        });
    }

    private void printProduct(GetProductDto productDto) {
        if (productDto.quantity() == 0) {
            System.out.printf(PRODUCT_NO_QUANTITY.message,
                    productDto.name(), productDto.price(), productDto.promotion());
            return;
        }
        System.out.printf(PRODUCT.message,
                productDto.name(), productDto.price(), productDto.quantity(), productDto.promotion());
    }

    public void printShortage(String name, int shortage) {
        System.out.printf(String.format(PROMOTION_SHORTAGE.message, name, shortage));
        printNewLine();
    }

    public void printAddPromotionProduct(String name) {
        System.out.printf(String.format(ADD_PROMOTION_PRODUCT_COUNT.message, name));
        printNewLine();
    }
}
