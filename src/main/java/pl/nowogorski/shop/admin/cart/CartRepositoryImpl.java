package pl.nowogorski.shop.admin.cart;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.nowogorski.shop.product.Product;
import pl.nowogorski.shop.product.ProductRepository;

import static java.time.LocalDateTime.now;

@Service
class CartRepositoryImpl {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    CartRepositoryImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart readCart(Long id) {
        return cartRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Cart addProductToCart(Long id, CartProductDto cartProductDto) {
        Cart cart = getInitializedCart(id);
        cart.addProduct(CartItem.builder()
                        .quantity(cartProductDto.quantity())
                        .product(getProduct(cartProductDto.productId()))
                        .cartId(cart.getId())
                .build());
        return cart;
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    private Cart getInitializedCart(Long id) {
        if(id == null || id <= 0){
            return cartRepository.save(Cart.builder().created(now()).build());
        }
        return null;
    }
}
