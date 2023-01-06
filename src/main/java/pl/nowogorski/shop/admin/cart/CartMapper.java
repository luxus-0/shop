package pl.nowogorski.shop.admin.cart;

import org.springframework.stereotype.Service;
import pl.nowogorski.shop.product.Product;

import java.math.BigDecimal;
import java.util.List;

@Service
class CartMapper {
    public static CartSummationDto mapToCartSummation(Cart cart){
        return CartSummationDto.builder()
                .id(cart.getId())
                .items(mapCartItems(cart.getItems()))
                .sum(mapToSummation(cart.getItems()))
                .build();
    }

    private static List<CartSummationItemsDTO> mapCartItems(List<CartItem> items) {
        return items.stream()
                .map(CartMapper::mapToCartItem)
                .toList();
    }

    private static CartSummationItemsDTO mapToCartItem(CartItem cartItem) {
        return CartSummationItemsDTO.builder()
                .id(cartItem.getId())
                .quantity(cartItem.getQuantity())
                .product(mapToProductDto(cartItem.getProduct()))
                .grossValue(calculateGrossValue(cartItem))
                .build();
    }

    private static ProductDTO mapToProductDto(Product product) {
        return ProductDTO.builder()
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

    private static SummationDTO mapToSummation(List<CartItem> items) {
        return SummationDTO.builder()
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
