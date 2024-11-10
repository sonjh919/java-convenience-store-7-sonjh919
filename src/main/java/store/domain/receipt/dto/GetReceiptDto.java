package store.domain.receipt.dto;

import java.util.List;

public record GetReceiptDto(List<GetPurchaseProductDto> purchaseProducts,
                            List<GetPromotionProductDto> promotionProducts,
                            GetAmountDto amount) {
}
