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
        System.out.println("purchaseProducts.getFirst().getName() = " + purchaseProducts.getFirst().getName());
        System.out.println("purchaseProducts.get(0).getPrice() = " + purchaseProducts.getFirst().getPrice());
        System.out.println("purchaseProducts.getFirst().getCount() = " + purchaseProducts.getFirst().getCount());
        System.out.println("-----------");
        System.out.println("promotionProducts.getFirst().getName() = " + promotionProducts.getFirst().getName());
        System.out.println("promotionProducts.getFirst().getCount() = " + promotionProducts.getFirst().getCount());

    }
}
