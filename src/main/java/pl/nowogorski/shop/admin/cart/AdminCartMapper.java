package pl.nowogorski.shop.admin.cart;

import org.springframework.stereotype.Service;
import pl.nowogorski.shop.product.Product;
import pl.nowogorski.shop.product.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

@Service
class AdminCartMapper {
    public static AdminCartSummaryDto mapToCartSummary(AdminCart adminCart){
        return AdminCartSummaryDto.builder()
                .id(adminCart.getId())
                .items(mapCartItems(adminCart.getItems()))
                .summary(mapToSummary(adminCart.getItems()))
                .build();
    }

    private static List<AdminCartSummaryItemDto> mapCartItems(List<AdminCartItem> items) {
        return items.stream()
                .map(AdminCartMapper::mapToCartItem)
                .toList();
    }

    private static AdminCartSummaryItemDto mapToCartItem(AdminCartItem adminCartItem) {
        return AdminCartSummaryItemDto.builder()
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

    private static BigDecimal calculateGrossValue(AdminCartItem adminCartItem) {
        return adminCartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(adminCartItem.getQuantity()));
    }

    private static AdminSummaryDto mapToSummary(List<AdminCartItem> items) {
        return AdminSummaryDto.builder()
                .grossValue(sumGrossValue(items))
                .build();
    }

    private static BigDecimal sumGrossValue(List<AdminCartItem> items) {
        return items.stream()
                .map(AdminCartMapper::calculateGrossValue)
                .reduce((BigDecimal::add))
                .orElse(BigDecimal.ZERO);
    }
}
