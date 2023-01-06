package pl.nowogorski.shop.admin.cart;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public record CartSummationDto(Long id, List<CartSummationItemsDTO> items, SummationDTO sum) {
}
