package pl.nowogorski.shop.admin.cart;

import org.springframework.stereotype.Service;

@Service
class CartItemImpl {

    private final CartItemRepository cartItemRepository;

    CartItemImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }
}
