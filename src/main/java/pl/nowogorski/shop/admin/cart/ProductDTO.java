package pl.nowogorski.shop.admin.cart;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public record ProductDTO(Long id, String name, BigDecimal price, String currency, String image, String slug){}
