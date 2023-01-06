package pl.nowogorski.shop.admin.cart;

import jakarta.persistence.*;
import lombok.*;
import pl.nowogorski.shop.product.Product;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @OneToOne
    private Product product;
    private Long cartId;
}
