package pl.nowogorski.shop.admin.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
