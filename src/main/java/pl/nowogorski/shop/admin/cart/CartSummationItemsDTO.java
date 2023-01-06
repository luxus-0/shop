package pl.nowogorski.shop.admin.cart;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public record CartSummationItemsDTO(Long id, int quantity, ProductDTO product, BigDecimal grossValue) {
}
