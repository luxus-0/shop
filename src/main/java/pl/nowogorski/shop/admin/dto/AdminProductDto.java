package pl.nowogorski.shop.admin.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public record AdminProductDto(String name, String category, String description, BigDecimal price, String currency) {
}
