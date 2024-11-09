package store.domain.receipt;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private List<PurchaseProduct> purchaseProducts;
    private List<PromotionProduct> promotionProducts;

    public Receipt() {
        this.purchaseProducts = new ArrayList<>();
        this.promotionProducts = new ArrayList<>();
    }

    public void addPurchaseProduct(PurchaseProduct purchaseProduct) {
        purchaseProducts.add(purchaseProduct);
    }

    public void addPromotionProduct(PromotionProduct promotionProduct){
        promotionProducts.add(promotionProduct);
    }

    public void print() {
        System.out.println("-----------");
        purchaseProducts.forEach(purchaseProduct -> {
            System.out.println("purchaseProduct.getName() = " + purchaseProduct.getName());
            System.out.println("purchaseProduct.getPrice() = " + purchaseProduct.getPrice());
            System.out.println("purchaseProduct.getCount() = " + purchaseProduct.getCount());
        });

        System.out.println("-----------");
        promotionProducts.forEach(promotionProduct -> {
            System.out.println("promotionProduct.getName() = " + promotionProduct.getName());
            System.out.println("promotionProduct.getCount() = " + promotionProduct.getCount());
        });
    }
}
