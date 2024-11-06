package store;

import store.controller.StoreController;
import store.domain.Products;
import store.global.config.AppConfig;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = AppConfig.INSTANCE;

        StoreController storeController = appConfig.createStoreController();
        Products products = appConfig.createProducts();

        storeController.start();
    }
}
