package store.domain.product;

import static store.global.exception.ExceptionMessage.NON_EXISTENT_PRODUCT;

import java.util.List;
import store.domain.product.dto.GetProductsDto;
import store.view.dto.GetPurchaseProductDto;
import store.view.dto.GetPurchaseProductsDto;

public class Products {
    private final List<Product> products;

    private Products(List<Product> products) {
        this.products = products;
    }

    public static Products from(List<Product> products) {
        return new Products(products);
    }

    public GetProductsDto getProducts() {
        return new GetProductsDto(products.stream()
                .map(Product::GetProductDto)
                .toList());
    }

    public void isAvailableProducts(GetPurchaseProductsDto getPurchaseProductsDto) {
        getPurchaseProductsDto.getPurchaseProductDtos()
                .forEach(this::validateExistProducts);
    }

    private void validateExistProducts(GetPurchaseProductDto getPurchaseProductDto) {
        validateIsAvailableProduct(getPurchaseProductDto);
        validateCanPurchaseProduct(getPurchaseProductDto);
    }

    private void validateIsAvailableProduct(GetPurchaseProductDto getPurchaseProductDto) {
        products.stream()
                .filter(product -> product.isSameName(getPurchaseProductDto.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NON_EXISTENT_PRODUCT.message));
    }

    private void validateCanPurchaseProduct(GetPurchaseProductDto getPurchaseProductDto) {
        int totalQuantity = products.stream()
                .filter(product -> product.isSameName(getPurchaseProductDto.name()))
                .mapToInt(Product::getQuantity)
                .sum();

        if (totalQuantity < getPurchaseProductDto.count()) {
            throw new IllegalArgumentException(NON_EXISTENT_PRODUCT.message);
        }
    }
}
