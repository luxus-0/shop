package pl.nowogorski.shop.admin.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
class CartController {

    private CartRepositoryImpl cartRepositoryImpl;

    CartController(CartRepositoryImpl cartRepositoryImpl) {
        this.cartRepositoryImpl = cartRepositoryImpl;
    }

    @GetMapping("/{id}")
    CartSummaryDto readCart(@PathVariable Long id){
        return CartMapper.mapToCartSummary(cartRepositoryImpl.readCart(id));
    }

    @PutMapping("/{id}")
    CartSummaryDto addProductToCart(@PathVariable Long id, @RequestBody CartProductDto cartProductDto) {
        return CartMapper.mapToCartSummary(cartRepositoryImpl.addProductToCart(id, cartProductDto));
    }

    @PutMapping("/{id}/update")
    CartSummaryDto updateCart(@PathVariable Long id, @RequestBody List<CartProductDto> cartProductDtos){
        return CartMapper.mapToCartSummary(cartRepositoryImpl.actualizeCart(id, cartProductDtos));

    }


}
