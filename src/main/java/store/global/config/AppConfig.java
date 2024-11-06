package store.global.config;

import store.controller.StoreController;
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
}
