package pl.nowogorski.shop.product.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import pl.nowogorski.shop.admin.ProductCurrency;

import java.math.BigDecimal;

public record ProductDto(@NotBlank @Length(min = 4) String name,
                         @NotBlank @Length(min = 4) String category,
                         @NotBlank @Length(min = 4) String description,
                         @NotBlank @Length(min = 4) BigDecimal price,
                         @NotBlank @Enumerated(value = EnumType.STRING) ProductCurrency currency,
                         String image) {
}
