package store.view.output;

import static store.view.output.Output.NEW_LINE;
import static store.view.output.Output.PRODUCT;
import static store.view.output.Output.PRODUCT_NO_QUANTITY;
import static store.view.output.Output.PRODUCT_SEPARATOR;
import static store.view.output.Output.PROMOTION_SHORTAGE;

import store.domain.product.dto.GetProductsDto;
import store.view.dto.GetPurchaseProductDto;

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
            System.out.printf(String.format(checkQuantity(productDto.quantity()),
                    productDto.name(), productDto.price(), productDto.quantity(), productDto.promotion()));
            printNewLine();
        });
    }

    private String checkQuantity(int quantity) {
        if (quantity == 0) {
            return PRODUCT_NO_QUANTITY.message;
        }
        return PRODUCT.message;
    }

    public void printShortage(String name, int shortage) {
        System.out.printf(String.format(PROMOTION_SHORTAGE.message, name, shortage));
        printNewLine();
    }
}
