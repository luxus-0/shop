package pl.nowogorski.shop.admin.cart;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate created;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cartId")
    private List<CartItem> items;

    void addProduct(CartItem cartItem){
        if(items == null){
            items = new ArrayList<>();
        }
        items.stream()
                .filter(item -> Objects.equals(cartItem.getProduct().getId(), item.getProduct().getId()))
                .findFirst()
                .ifPresentOrElse(
                        item -> item.setQuantity(item.getQuantity() + 1),
                        () -> items.add(cartItem)
                );
    }
}
