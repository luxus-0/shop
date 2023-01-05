package pl.nowogorski.shop.product.dto;

import lombok.Builder;

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
