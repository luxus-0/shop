package pl.nowogorski.shop.admin.cart;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AdminProductDto(Long id, String name, BigDecimal price, String currency, String image, String slug){}
