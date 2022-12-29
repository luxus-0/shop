package pl.nowogorski.shop.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
record Product(@Id @GeneratedValue Long id, String name, String category,
               String description, BigDecimal price, String currency) {
}
