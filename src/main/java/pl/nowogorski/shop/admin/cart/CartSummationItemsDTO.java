package pl.nowogorski.shop.admin.cart;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CartSummationItemsDTO(Long id, int quantity, ProductDTO product, BigDecimal grossValue) {
}
