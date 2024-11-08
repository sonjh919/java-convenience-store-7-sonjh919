package store.domain.product;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import store.domain.promotion.Promotions;
import store.domain.receipt.PurchaseProducts;
import store.global.config.ProductFactory;
import store.global.config.PromotionFactory;

public class PurchaseProductsTest {
    private Promotions promotions;
    private Products products;
    private PurchaseProducts purchaseProducts;


    @BeforeEach
    void setUp() {
        promotions = new PromotionFactory().createPromotions();
        products = new ProductFactory().createProducts();

    }

    @Nested
    class Products_구매_검증_테스트 {
        @Test
        void 구매_성공_테스트() {
            purchaseProducts = PurchaseProducts.from("[사이다-15],[감자칩-1]");

            assertThatCode(() -> purchaseProducts.isAvailableProducts())
                    .doesNotThrowAnyException();
        }

        @Test
        void 구매_수량이_재고_수량을_초과한_경우_예외가_발생한다() {
            purchaseProducts = PurchaseProducts.from("[사이다-16],[감자칩-1]");

            assertThatThrownBy(() -> purchaseProducts.isAvailableProducts())
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 존재하지_않는_상품을_입력한_경우_예외가_발생한다() {
            assertThatThrownBy(() -> PurchaseProducts.from("[존재하지않는상품-1]"))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
