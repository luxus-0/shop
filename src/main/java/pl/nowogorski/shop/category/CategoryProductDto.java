package pl.nowogorski.shop.category;

import org.springframework.data.domain.Page;
import pl.nowogorski.shop.product.Product;

public record CategoryProductDto(Category category, Page<Product> page) {
}
