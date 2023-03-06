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
class AdminCartService {

    private AdminCartRepository adminCartRepository;
    private ProductRepository productRepository;

    AdminCartService(AdminCartRepository adminCartRepository, ProductRepository productRepository) {
        this.adminCartRepository = adminCartRepository;
        this.productRepository = productRepository;
    }


    public AdminCart readCart(Long id) {
        return adminCartRepository.findById(id).orElseThrow();
    }

    @Transactional
    public AdminCart addProductToCart(Long id, AdminCartProductDto adminCartProductDto) {
        AdminCart adminCart = getInitializedCart(id);
        adminCart.addProduct(AdminCartItem.builder()
                        .quantity(adminCartProductDto.quantity())
                        .product(getProduct(adminCartProductDto.productId()))
                        .cartId(adminCart.getId())
                .build());
        return adminCart;
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    private AdminCart getInitializedCart(Long id) {
        if (id == null || id <= 0) {
            return saveNewCart();
        }
        return getCart(id);
    }

    private AdminCart getCart(Long id) {
        return adminCartRepository.findById(id)
                .orElseGet(this::saveNewCart);
    }

    private AdminCart saveNewCart() {
        return adminCartRepository.save(
                AdminCart.builder()
                        .created(LocalDate.now())
                        .build());
    }

    @Transactional
    public AdminCart actualizeCart(Long id, List<AdminCartProductDto> adminCartProductDtos) {
        AdminCart adminCart = adminCartRepository.findById(id).orElseThrow();
        adminCart.getItems().forEach(cartItem -> adminCartProductDtos.stream()
                .filter(adminCartProductDto -> cartItem.getProduct().getId().equals(adminCartProductDto.productId()))
                .findFirst()
                .ifPresent(adminCartProductDto -> cartItem.setQuantity(adminCartProductDto.quantity())));
        return adminCart;
    }
}
