package pl.nowogorski.shop.admin.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.nowogorski.shop.product.Product;
import pl.nowogorski.shop.product.ProductRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
class CartService {

    private CartRepository cartRepository;
    private ProductRepository productRepository;

    CartService(CartRepository cartRepository, ProductRepository productRepository) {
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
        if (id == null || id <= 0) {
            return saveNewCart();
        }
        return getCart(id);
    }

    private Cart getCart(Long id) {
        return cartRepository.findById(id)
                .orElseGet(this::saveNewCart);
    }

    private Cart saveNewCart() {
        return cartRepository.save(
                Cart.builder()
                        .created(LocalDate.now())
                        .build());
    }

    @Transactional
    public Cart actualizeCart(Long id, List<CartProductDto> cartProductDtos) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        cart.getItems().forEach(cartItem -> cartProductDtos.stream()
                .filter(cartProductDto -> cartItem.getProduct().getId().equals(cartProductDto.productId()))
                .findFirst()
                .ifPresent(cartProductDto -> cartItem.setQuantity(cartProductDto.quantity())));
        return cart;
    }
}
