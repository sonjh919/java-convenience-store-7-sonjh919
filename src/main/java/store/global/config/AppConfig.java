package store.global.config;

import store.controller.StoreController;
import store.domain.product.Products;
import store.domain.promotion.Promotions;
import store.view.input.InputView;
import store.view.output.OutputView;
import store.view.View;

public enum AppConfig {
    INSTANCE;

    public StoreController createStoreController() {
        return new StoreController(createView(), createPromotions(), createProducts());
    }

    public View createView() {
        return new View(createInputView(), createOutputView());
    }

    private InputView createInputView() {
        return new InputView();
    }

    private OutputView createOutputView() {
        return new OutputView();
    }

    private Products createProducts() {
        return new ProductFactory().createProducts();
    }

    private Promotions createPromotions() {
        return new PromotionFactory().createPromotions();
    }
}
