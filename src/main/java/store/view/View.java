package store.view;

import static store.view.output.Output.PRODUCT_LIST;
import static store.view.output.Output.PURCHASE;
import static store.view.output.Output.WELCOME;

import store.domain.product.dto.GetProductsDto;
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

    public void outputProducts(GetProductsDto products) {
        outputView.printMessage(WELCOME);
        outputView.printMessage(PRODUCT_LIST);

        outputView.printProducts(products);
    }

    public String purchase() {
        outputView.printNewLine();
        outputView.printMessage(PURCHASE);

        return inputView.userInput();
    }

    public Answer inputShortageProduct(String name, int shortage) {
        outputView.printShortage(name, shortage);

        return Answer.from(inputView.userInput());
    }

    public Answer inputAddPromotionProduct(String name) {
        outputView.printAddPromotionProduct(name);

        return Answer.from(inputView.userInput());
    }
}
