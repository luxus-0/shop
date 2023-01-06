package pl.nowogorski.shop.admin.cart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.time.LocalDateTime.now;


/**
 * Cron delete old cart:
 * from today to 3 days before
 */

@Service
@Slf4j
class CartOldRemoval {

    private final CartRepository cartRepository;

    CartOldRemoval(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Scheduled(cron = "${cron.remove.old.carts}")
    void removeOldCarts(){
        List<Cart> carts = cartRepository.findByCreatedLessThan(now().minusDays(3));
        log.info("Old carts:" + carts.size());
    }
}
