package store.domain.receipt;

public class PromotionProduct {
    private String name;
    private int count;

    public PromotionProduct(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
