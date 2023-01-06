package pl.nowogorski.shop.admin.cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Long countByCartId(Long cardId);
}
