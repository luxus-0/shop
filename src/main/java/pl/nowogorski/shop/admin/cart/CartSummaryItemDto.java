package pl.nowogorski.shop.admin.cart;

import lombok.Builder;
import pl.nowogorski.shop.product.dto.ProductDto;

import java.math.BigDecimal;

@Builder
public record CartSummaryItemDto(Long id, int quantity, ProductDto product, BigDecimal grossValue) {
}
