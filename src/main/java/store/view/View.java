package store.view;

import static store.view.output.Output.CONTINUE;
import static store.view.output.Output.MEMBERSHIP_DISCOUNT;
import static store.view.output.Output.PRODUCT_LIST;
import static store.view.output.Output.PURCHASE;
import static store.view.output.Output.WELCOME;

import store.domain.product.dto.GetProductsDto;
import store.domain.receipt.dto.GetReceiptDto;
import store.view.input.Answer;
import store.view.input.InputView;
import store.view.output.OutputView;

public class View {
    private final InputView inputView;
    private final OutputView outputView;

    public View(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void outputProducts(final GetProductsDto products) {
        outputView.printMessage(WELCOME);
        outputView.printMessage(PRODUCT_LIST);

        outputView.printProducts(products);
    }

    public String purchase() {
        outputView.printNewLine();
        outputView.printMessage(PURCHASE);

        return inputView.userInput();
    }

    public Answer inputShortageProduct(final String name, final int shortage) {
        outputView.printNewLine();
        outputView.printShortage(name, shortage);

        return Answer.from(inputView.userInput());
    }

    public Answer inputAddPromotionProduct(final String name) {
        outputView.printNewLine();
        outputView.printAddPromotionProduct(name);

        return Answer.from(inputView.userInput());
    }

    public Answer inputMembershipDiscount() {
        outputView.printNewLine();
        outputView.printMessage(MEMBERSHIP_DISCOUNT);

        return Answer.from(inputView.userInput());
    }

    public Answer inputContinue() {
        outputView.printNewLine();
        outputView.printMessage(CONTINUE);

        Answer answer = Answer.from(inputView.userInput());
        outputView.printNewLine();
        return answer;
    }

    public void outputReceipt(final GetReceiptDto receipt) {
        outputView.printReceipt(receipt);
    }
}
