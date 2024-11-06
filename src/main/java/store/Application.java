package store;

import store.controller.StoreController;
import store.domain.Products;
import store.domain.Promotions;
import store.global.config.AppConfig;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = AppConfig.INSTANCE;

        StoreController storeController = appConfig.createStoreController();
        Products products = appConfig.createProducts();
        Promotions promotions = appConfig.createPromotions();

        storeController.start();
    }
}
