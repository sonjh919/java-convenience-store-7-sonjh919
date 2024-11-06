package store;

import store.controller.StoreController;
import store.global.config.AppConfig;

public class Application {
    public static void main(String[] args) {
        StoreController storeController = AppConfig.INSTANCE.createStoreController();
        storeController.start();
    }
}
