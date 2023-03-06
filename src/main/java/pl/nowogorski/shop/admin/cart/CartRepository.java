package pl.nowogorski.shop.admin.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByCreatedLessThan(LocalDateTime minusDays);
    @Query("delete from Cart c where c.id =: id")
    @Modifying
    void deleteCardById(Long id);

    @Query("delete from Cart c where c.id in (:cartId)")
    @Modifying
    void deleteAllByIdIn(List<Long> cartId);
}
