package pl.nowogorski.shop.product.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;
import pl.nowogorski.shop.admin.AdminProductCurrency;

import java.math.BigDecimal;

@Builder
public class ProductListDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String currency;
    private String image;
    private String slug;
}
