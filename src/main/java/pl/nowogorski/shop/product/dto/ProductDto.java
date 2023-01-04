package pl.nowogorski.shop.product.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import pl.nowogorski.shop.admin.AdminProductCurrency;

import java.math.BigDecimal;

public record ProductDto(@NotBlank @Length(min = 4) String name,
                         @NotBlank @Length(min = 4) String category,
                         @NotBlank @Length(min = 4) String description,
                         String fullDescription,
                         @NotBlank @Length(min = 4) BigDecimal price,
                         @NotBlank @Enumerated(value = EnumType.STRING) AdminProductCurrency currency,
                         String image,
                         @NotBlank @Length(min = 4) String slug) {
}
