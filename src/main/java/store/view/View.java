package store.view;

import static store.view.Output.PRODUCT_LIST;
import static store.view.Output.PURCHASE;
import static store.view.Output.WELCOME;

import store.domain.product.dto.GetProductsDto;
import store.view.dto.GetPurchaseProductsDto;

public class View {
    private final InputView inputView;
    private final OutputView outputView;
    private final InputParser inputParser;

    public View(final InputView inputView, final OutputView outputView, InputParser inputParser) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputParser = inputParser;
    }

    public void outputProducts(GetProductsDto products) {
        outputView.printMessage(WELCOME);
        outputView.printMessage(PRODUCT_LIST);

        outputView.printProducts(products);
    }

    public GetPurchaseProductsDto purchase() {
        outputView.printNewLine();
        outputView.printMessage(PURCHASE);

        return inputParser.purchase(inputView.userInput());
    }
}
