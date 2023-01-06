package pl.nowogorski.shop.admin.cart;

import lombok.Builder;

import java.util.List;

@Builder
public record CartSummationDto(Long id, List<CartSummationItemsDTO> items, SummationDTO sum) {
}
