package store.domain.product;

import java.util.List;
import store.domain.product.dto.GetProductsDto;

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

}
