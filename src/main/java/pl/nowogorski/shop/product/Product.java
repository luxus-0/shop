package pl.nowogorski.shop.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.nowogorski.shop.admin.AdminProductCurrency;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private String description;
    private BigDecimal price;
    @Enumerated(value = EnumType.STRING)
    private AdminProductCurrency currency;
    private String image;
    private String slug;
}

