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
class AdminCartOldRemoval {

    private final AdminCartRepository adminCartRepository;
    private final AdminCartItemRepository adminCartItemRepository;

    AdminCartOldRemoval(AdminCartRepository adminCartRepository, AdminCartItemRepository adminCartItemRepository) {
        this.adminCartRepository = adminCartRepository;
        this.adminCartItemRepository = adminCartItemRepository;
    }

    @Transactional
    @Scheduled(cron = "${cron.remove.old.carts}")
    void removeOldCarts(){
        List<AdminCart> adminCarts = adminCartRepository.findByCreatedLessThan(now().minusDays(3));
        List<Long> cartId = adminCarts.stream()
                .map(AdminCart::getId)
                .toList();
        if(!cartId.isEmpty()){
            adminCartItemRepository.deleteAllByCardIdIn(cartId);
            adminCartRepository.deleteAllByIdIn(cartId);
        }
    }
}