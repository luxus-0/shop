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
class AdminAdminCartRepositoryImplTest {

    @Mock
    private AdminCartRepository adminCartRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private AdminCartService adminCartService;

    @Test
    void shouldAddProductToCartWhenCardIdNotExists(){
        //given
        Long cartId = 1L;
        AdminCartProductDto adminCartProductDto = new AdminCartProductDto(1L, 1);
        when(productRepository.findById(1L)).thenReturn(Optional.of(Product.builder().id(1L).build()));
        when(adminCartRepository.findById(cartId)).thenReturn(Optional.of(AdminCart.builder().id(1L).build()));
        //when
        AdminCart result = adminCartService.addProductToCart(cartId, adminCartProductDto);
        //then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(1L);
    }

}