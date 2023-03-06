package pl.nowogorski.shop.admin.cart;

import org.springframework.stereotype.Service;

@Service
class AdminCartItemImpl {

    private final AdminCartItemRepository adminCartItemRepository;

    AdminCartItemImpl(AdminCartItemRepository adminCartItemRepository) {
        this.adminCartItemRepository = adminCartItemRepository;
    }

    public void deleteCartItem(Long id) {
        adminCartItemRepository.deleteById(id);
    }

    public Long countItemInCart(Long cartId) {
        return adminCartItemRepository.countByCartId(cartId);
    }
}
