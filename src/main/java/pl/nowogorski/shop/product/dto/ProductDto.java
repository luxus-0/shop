package pl.nowogorski.shop.product.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.hibernate.validator.constraints.Length;
import pl.nowogorski.shop.admin.ProductCurrency;

import java.math.BigDecimal;

public record ProductDto(@Length(min = 4) String name, @Length(min = 4) String category,
                         @Length(min = 4) String description,
                         @Length(min = 4) BigDecimal price,
                         @Enumerated(value = EnumType.STRING) ProductCurrency currency) {
}
