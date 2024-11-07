package store.view;

import static store.view.Output.NEW_LINE;
import static store.view.Output.PRODUCT;
import static store.view.Output.PRODUCT_SEPARATOR;

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
            System.out.printf(String.format(PRODUCT.message,
                    productDto.name(), productDto.price(), productDto.quantity(), productDto.promotion()));
            printNewLine();
        });
    }
}
