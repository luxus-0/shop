package pl.nowogorski.shop.product.dto;

import java.math.BigDecimal;

public record ProductDto(String name, String category, String description, BigDecimal price, String currency) {
}
