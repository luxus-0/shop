package pl.nowogorski.shop.admin.cart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final CartItemRepository cartItemRepository;

    CartOldRemoval(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Transactional
    @Scheduled(cron = "${cron.remove.old.carts}")
    void removeOldCarts(){
        List<Cart> carts = cartRepository.findByCreatedLessThan(now().minusDays(3));
        List<Long> cartId = carts.stream()
                .map(Cart::getId)
                .toList();
        if(!cartId.isEmpty()){
            cartItemRepository.deleteAllByCardIdIn(cartId);
            cartRepository.deleteAllByIdIn(cartId);
        }
        /*carts.forEach(cart -> {
            cartItemRepository.deleteByCartId(cart.getId());
            cartRepository.deleteCardById(cart.getId());
        });*/

    }
}
