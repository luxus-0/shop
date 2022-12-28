package pl.nowogorski.shop.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
class Product {
    private String name;
    private String category;
    private String description;
    private BigDecimal price;
    private String currency;
}
