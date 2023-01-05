package pl.nowogorski.shop.admin.cart;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.nowogorski.shop.product.Product;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @OneToOne
    private Product product;
    private Long cartId;
}
