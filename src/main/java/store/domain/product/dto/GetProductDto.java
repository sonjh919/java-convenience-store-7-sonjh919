package store.domain.product.dto;

import store.domain.product.Product;

public record GetProductDto(String name, int price, int quantity, String promotion) {}
