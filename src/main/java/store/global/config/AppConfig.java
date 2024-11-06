package store.global.config;

import store.controller.StoreController;
import store.domain.Products;
import store.domain.Promotions;
import store.view.InputView;
import store.view.OutputView;
import store.view.View;

public enum AppConfig {
    INSTANCE;

    public StoreController createStoreController() {
        return new StoreController(createView());
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

    public Products createProducts() {
        return new ProductFactory().createProducts();
    }

    public Promotions createPromotions() {
        return new PromotionFactory().createPromotions();
    }
}
