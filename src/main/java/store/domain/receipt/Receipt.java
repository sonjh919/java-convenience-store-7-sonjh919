package store.domain.receipt;

import java.util.ArrayList;
import java.util.List;
import store.domain.receipt.dto.GetAmountDto;
import store.domain.receipt.dto.GetPromotionProductDto;
import store.domain.receipt.dto.GetPurchaseProductDto;
import store.domain.receipt.dto.GetReceiptDto;

public class Receipt {
    private static final int MEMBERSHIP_DISCOUNT_LIMIT = 8000;
    private static final double MEMBERSHIP_DISCOUNT_RATE = 0.3;

    private List<PurchaseProduct> purchaseProducts;
    private List<PromotionProduct> promotionProducts;
    private int membershipDiscount;

    public Receipt() {
        this.purchaseProducts = new ArrayList<>();
        this.promotionProducts = new ArrayList<>();
        this.membershipDiscount = 0;
    }

    public void addPurchaseProduct(PurchaseProduct purchaseProduct) {
        purchaseProducts.add(purchaseProduct);
    }

    public void addPromotionProduct(PromotionProduct promotionProduct) {
        promotionProducts.add(promotionProduct);
    }

    public GetReceiptDto getReceipt() {
        return new GetReceiptDto(getPurchaseProductDtos(),
                getPromotionProductDtos(),
                getAmountDto());
    }

    private GetAmountDto getAmountDto() {
        return new GetAmountDto(getTotalCount(),
                getTotalPrice(),
                -getPromotionDiscountPrice(),
                -membershipDiscount,
                getTotalPrice() - getPromotionDiscountPrice() - membershipDiscount);
    }

    private List<GetPurchaseProductDto> getPurchaseProductDtos() {
        return purchaseProducts.stream()
                .map(PurchaseProduct::getPurchaseProductDto)
                .toList();
    }

    private List<GetPromotionProductDto> getPromotionProductDtos() {
        return promotionProducts.stream()
                .map(PromotionProduct::getPromotionProductDto)
                .toList();
    }

    public void print() {
        System.out.println();
        System.out.println("==============W 편의점================");
        System.out.println("상품명\t\t수량\t금액");
        purchaseProducts.forEach(purchaseProduct -> {
            System.out.printf("%s\t\t%,d\t%,d", purchaseProduct.getName(), purchaseProduct.getCount(),
                    purchaseProduct.getCount() * purchaseProduct.getPrice());
            System.out.println();
        });

        System.out.println("=============증\t정===============");
        promotionProducts.forEach(promotionProduct -> {
            if (promotionProduct.getCount() > 0) {
                System.out.printf("%s\t\t%,d\n", promotionProduct.getName(), promotionProduct.getCount());
            }
        });

        System.out.println();
        System.out.println("====================================");
        System.out.printf("총구매액\t\t%,d\t%,d", getTotalCount(), getTotalPrice());
        System.out.println();
        System.out.printf("행사할인\t\t\t%,d", -getPromotionDiscountPrice());
        System.out.println();
        System.out.printf("멤버십할인\t\t\t%,d", -membershipDiscount);
        System.out.println();
        System.out.printf("내실돈\t\t\t%,d", (getTotalPrice() - getPromotionDiscountPrice() - membershipDiscount));
        System.out.println();
    }

    private int getTotalCount() {
        return purchaseProducts.stream()
                .mapToInt(PurchaseProduct::getCount)
                .sum();
    }

    public void getMembershipDiscount() {
        int totalPrice = getTotalPrice();
        int promotionDiscountPrice = getTotalPromotionDiscountPrice();
        int membershipDiscountPrice = (int) ((totalPrice - promotionDiscountPrice) * MEMBERSHIP_DISCOUNT_RATE);

        this.membershipDiscount = validateMembershipDiscountPrice(totalPrice, promotionDiscountPrice,
                membershipDiscountPrice);
    }

    private int getTotalPromotionDiscountPrice() {
        return promotionProducts.stream()
                .mapToInt(PromotionProduct::getTotalPrices)
                .sum();
    }

    private int validateMembershipDiscountPrice(int totalPrice, int promotionDiscountPrice,
                                                int membershipDiscountPrice) {
        if (membershipDiscountPrice > MEMBERSHIP_DISCOUNT_LIMIT) {
            membershipDiscountPrice = MEMBERSHIP_DISCOUNT_LIMIT;
        }

        if (totalPrice < promotionDiscountPrice + membershipDiscountPrice) {
            membershipDiscountPrice = totalPrice - promotionDiscountPrice;
        }
        return membershipDiscountPrice;
    }

    private int getTotalPrice() {
        return purchaseProducts.stream()
                .mapToInt(PurchaseProduct::getPrices)
                .sum();
    }

    private int getPromotionDiscountPrice() {
        return promotionProducts.stream()
                .mapToInt(PromotionProduct::getPrices)
                .sum();
    }

    public void clear() {
        purchaseProducts.clear();
        promotionProducts.clear();
        membershipDiscount = 0;
    }
}
