package store.domain.receipt;

public class PromotionProduct {
    private String name;
    private int count;

    private PromotionProduct(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public static PromotionProduct of(String name, int count) {
        return new PromotionProduct(name, count);
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
