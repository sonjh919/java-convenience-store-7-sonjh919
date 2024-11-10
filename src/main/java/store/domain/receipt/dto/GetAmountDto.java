package store.domain.receipt.dto;

public record GetAmountDto(int totalCount, int totalPrice, int promotionDiscountPrice, int membershipDiscountPrice,
                           int finalPrice) {
}
