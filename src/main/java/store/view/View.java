package store.view;

import static store.view.Output.PRODUCT_LIST;
import static store.view.Output.WELCOME;

import store.domain.product.dto.GetProductsDto;

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
}
