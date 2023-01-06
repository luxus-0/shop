package pl.nowogorski.shop.admin.cart;

import lombok.Builder;

import java.util.List;

@Builder
public record CartSummaryDto(Long id, List<CartSummaryItemDto> items, SummaryDTO summary) {
}
