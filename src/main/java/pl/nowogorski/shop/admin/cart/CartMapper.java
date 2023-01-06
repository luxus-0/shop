package pl.nowogorski.shop.admin.cart;

import org.springframework.stereotype.Service;
import pl.nowogorski.shop.product.Product;

import java.math.BigDecimal;
import java.util.List;

@Service
class CartMapper {
    public static CartSummaryDto mapToCartSummation(Cart cart){
        return CartSummaryDto.builder()
                .id(cart.getId())
                .items(mapCartItems(cart.getItems()))
                .sum(mapToSummation(cart.getItems()))
                .build();
    }

    private static List<CartSummaryItemDto> mapCartItems(List<CartItem> items) {
        return items.stream()
                .map(CartMapper::mapToCartItem)
                .toList();
    }

    private static CartSummaryItemDto mapToCartItem(CartItem cartItem) {
        return CartSummaryItemDto.builder()
                .id(cartItem.getId())
                .quantity(cartItem.getQuantity())
                .product(mapToProductDto(cartItem.getProduct()))
                .grossValue(calculateGrossValue(cartItem))
                .build();
    }

    private static ProductDto mapToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .image(product.getImage())
                .slug(product.getSlug())
                .build();
    }

    private static BigDecimal calculateGrossValue(CartItem cartItem) {
        return cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }

    private static SummaryDTO mapToSummation(List<CartItem> items) {
        return SummaryDTO.builder()
                .grossValue(sumGrossValue(items))
                .build();
    }

    private static BigDecimal sumGrossValue(List<CartItem> items) {
        return items.stream()
                .map(CartMapper::calculateGrossValue)
                .reduce((BigDecimal::add))
                .orElse(BigDecimal.ZERO);
    }
}
