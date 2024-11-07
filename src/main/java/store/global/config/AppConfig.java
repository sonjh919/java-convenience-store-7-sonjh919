package store.global.config;

import store.controller.StoreController;
import store.domain.product.Products;
import store.domain.promotion.Promotions;
import store.view.InputParser;
import store.view.InputView;
import store.view.OutputView;
import store.view.View;

public enum AppConfig {
    INSTANCE;

    public StoreController createStoreController() {
        return new StoreController(createView(), createPromotions(), createProducts());
    }

    public View createView() {
        return new View(createInputView(), createOutputView(), createInputParser());
    }

    private InputView createInputView() {
        return new InputView();
    }

    private InputParser createInputParser() {
        return new InputParser();
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
