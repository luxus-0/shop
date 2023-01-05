package pl.nowogorski.shop.category;

import org.springframework.data.domain.Page;
import pl.nowogorski.shop.product.Product;
import pl.nowogorski.shop.product.dto.ProductListDto;

public record CategoryProductDto(Category category, Page<ProductListDto> page) {
}
