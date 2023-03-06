package pl.nowogorski.shop.admin.cart;

import org.springframework.stereotype.Service;
import pl.nowogorski.shop.product.Product;
import pl.nowogorski.shop.product.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

@Service
class CartMapper {
    public static CartSummaryDto mapToCartSummary(Cart cart){
        return CartSummaryDto.builder()
                .id(cart.getId())
                .items(mapCartItems(cart.getItems()))
                .summary(mapToSummary(cart.getItems()))
                .build();
    }

    private static List<CartSummaryItemDto> mapCartItems(List<CartItem> items) {
        return items.stream()
                .map(CartMapper::mapToCartItem)
                .toList();
    }

    private static CartSummaryItemDto mapToCartItem(CartItem adminCartItem) {
        return CartSummaryItemDto.builder()
                .id(adminCartItem.getId())
                .quantity(adminCartItem.getQuantity())
                .product(mapToProductDto(adminCartItem.getProduct()))
                .grossValue(calculateGrossValue(adminCartItem))
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

    private static BigDecimal calculateGrossValue(CartItem adminCartItem) {
        return adminCartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(adminCartItem.getQuantity()));
    }

    private static SummaryDto mapToSummary(List<CartItem> items) {
        return SummaryDto.builder()
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
