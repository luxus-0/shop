package pl.nowogorski.shop.admin.cart;

import lombok.Builder;

import java.util.List;

@Builder
public record AdminCartSummaryDto(Long id, List<AdminCartSummaryItemDto> items, AdminSummaryDto summary) {
}
