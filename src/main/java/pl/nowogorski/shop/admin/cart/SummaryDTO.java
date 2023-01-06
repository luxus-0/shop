package pl.nowogorski.shop.admin.cart;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record SummaryDTO(BigDecimal grossValue) {
}