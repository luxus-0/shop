package pl.nowogorski.shop.admin.cart;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.nowogorski.shop.product.Product;
import pl.nowogorski.shop.product.ProductRepository;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartRepositoryImplTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private CartService cartService;

    @Test
    void shouldAddProductToCartWhenCardIdNotExists(){
        //given
        Long cartId = 1L;
        CartProductDto cartProductDto = new CartProductDto(1L, 1);
        when(productRepository.findById(1L)).thenReturn(Optional.of(Product.builder().id(1L).build()));
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(Cart.builder().id(1L).build()));
        //when
        Cart result = cartService.addProductToCart(cartId, cartProductDto);
        //then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(1L);
    }

}