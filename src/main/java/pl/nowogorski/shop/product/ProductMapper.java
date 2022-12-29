package pl.nowogorski.shop.product;

import pl.nowogorski.shop.product.dto.ProductDto;

class ProductMapper {
    static ProductDto toDto(Product product){
        return new ProductDto(
                product.getName(),
                product.getCategory(),
                product.getDescription(),
                product.getPrice(),
                product.getCurrency());
    }
}
