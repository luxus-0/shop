package pl.nowogorski.shop.admin.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Long countByCartId(Long cardId);
    @Query("delete from CartItem ci where ci.cartId =: cartId")
    @Modifying
    void deleteByCartId(Long id);

    @Query("delete from CartItem ci where ci.cartId in (:cartId)")
    @Modifying
    void deleteAllByCardIdIn(List<Long> cartId);
}
