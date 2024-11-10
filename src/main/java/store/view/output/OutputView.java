package store.view.output;

import static store.view.output.Output.ADD_PROMOTION_PRODUCT_COUNT;
import static store.view.output.Output.NEW_LINE;
import static store.view.output.Output.PRODUCT;
import static store.view.output.Output.PRODUCT_NO_QUANTITY;
import static store.view.output.Output.PRODUCT_SEPARATOR;
import static store.view.output.Output.PROMOTION_SHORTAGE;
import static store.view.output.OutputReceipt.AMOUNT_HEADER;
import static store.view.output.OutputReceipt.MEMBERSHIP_DISCOUNT_AMOUNT;
import static store.view.output.OutputReceipt.MEMBERSHIP_DISCOUNT_ZERO;
import static store.view.output.OutputReceipt.PAY_AMOUNT;
import static store.view.output.OutputReceipt.PROMOTION_DISCOUNT_AMOUNT;
import static store.view.output.OutputReceipt.PROMOTION_HEADER;
import static store.view.output.OutputReceipt.PROMOTION_PRODUCT;
import static store.view.output.OutputReceipt.PURCHASE_HEADER;
import static store.view.output.OutputReceipt.PURCHASE_PRODUCT;
import static store.view.output.OutputReceipt.START_RECEIPT;
import static store.view.output.OutputReceipt.TOTAL_AMOUNT;

import java.util.List;
import store.domain.product.dto.GetProductDto;
import store.domain.product.dto.GetProductsDto;
import store.domain.receipt.dto.GetAmountDto;
import store.domain.receipt.dto.GetPromotionProductDto;
import store.domain.receipt.dto.GetPurchaseProductDto;
import store.domain.receipt.dto.GetReceiptDto;

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

    public void printReceipt(GetReceiptDto receipt) {
        printNewLine();
        System.out.println(START_RECEIPT.message);
        System.out.println(PURCHASE_HEADER.message);
        printPurchaseProducts(receipt.purchaseProducts());
        printPromotionProducts(receipt.promotionProducts());
        printAmount(receipt.amount());
    }

    private void printPurchaseProducts(List<GetPurchaseProductDto> getPurchaseProductDtos) {
        getPurchaseProductDtos.forEach(purchaseProduct -> {
            System.out.printf(PURCHASE_PRODUCT.message, purchaseProduct.name(), purchaseProduct.quantity(),
                    purchaseProduct.quantity() * purchaseProduct.price());
            printNewLine();
        });
    }

    private void printPromotionProducts(List<GetPromotionProductDto> getPromotionProductDtos) {
        System.out.println(PROMOTION_HEADER.message);
        getPromotionProductDtos.forEach(promotionProduct -> {
            if (promotionProduct.count() > 0) {
                System.out.printf(PROMOTION_PRODUCT.message, promotionProduct.name(), promotionProduct.count());
                printNewLine();
            }
        });
    }

    private void printAmount(GetAmountDto amount) {
        System.out.println(AMOUNT_HEADER.message);
        System.out.printf(TOTAL_AMOUNT.message, amount.totalCount(), amount.totalPrice());
        System.out.printf(PROMOTION_DISCOUNT_AMOUNT.message, -amount.promotionDiscountPrice());
        printMembershipDiscount(amount.membershipDiscountPrice());
        System.out.printf(PAY_AMOUNT.message, amount.finalPrice());
    }

    private void printMembershipDiscount(int membershipDiscountPrice) {
        if (membershipDiscountPrice == 0) {
            System.out.printf(MEMBERSHIP_DISCOUNT_ZERO.message);
            return;
        }
        System.out.printf(MEMBERSHIP_DISCOUNT_AMOUNT.message, -membershipDiscountPrice);
    }
}
