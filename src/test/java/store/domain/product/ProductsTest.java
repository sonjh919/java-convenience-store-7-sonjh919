package store.domain.product;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import store.global.config.ProductFactory;
import store.view.dto.GetPurchaseProductDto;
import store.view.dto.GetPurchaseProductsDto;

public class ProductsTest {
    private Products products;

    @BeforeEach
    void setUp() {
        products = new ProductFactory().createProducts();
    }

    @Nested
    class Products_구매_검증_테스트 {
        @Test
        void 구매_성공_테스트() {
            GetPurchaseProductsDto getPurchaseProductsDto = new GetPurchaseProductsDto(List.of(
                    new GetPurchaseProductDto("사이다", 15),
                    new GetPurchaseProductDto("감자칩", 1)
            ));

            assertThatCode(() -> products.isAvailableProducts(getPurchaseProductsDto))
                    .doesNotThrowAnyException();
        }

        @Test
        void 구매_수량이_재고_수량을_초과한_경우_예외가_발생한다() {
            GetPurchaseProductsDto getPurchaseProductsDto = new GetPurchaseProductsDto(List.of(
                    new GetPurchaseProductDto("사이다", 16),
                    new GetPurchaseProductDto("감자칩", 1)
            ));

            assertThatThrownBy(() -> products.isAvailableProducts(getPurchaseProductsDto))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 존재하지_않는_상품을_입력한_경우_예외가_발생한다() {
            GetPurchaseProductsDto getPurchaseProductsDto = new GetPurchaseProductsDto(List.of(
                    new GetPurchaseProductDto("존재하지않는상품", 1)
            ));

            assertThatThrownBy(() -> products.isAvailableProducts(getPurchaseProductsDto))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
