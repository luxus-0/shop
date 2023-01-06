package pl.nowogorski.shop.admin.cart;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CartSummaryItemDto(Long id, int quantity, ProductDto product, BigDecimal grossValue) {
}
