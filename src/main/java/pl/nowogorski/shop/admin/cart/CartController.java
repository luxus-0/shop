package pl.nowogorski.shop.admin.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    CartSummationDto readCart(@PathVariable Long id){
        return CartMapper.mapToCartSummation(cartRepositoryImpl.readCart(id));
    }

    @PutMapping("/{id}")
    CartSummationDto addProductToCart(@PathVariable Long id,@RequestBody CartProductDto cartProductDto){
        return CartMapper.mapToCartSummation(cartRepositoryImpl.addProductToCart(id, cartProductDto));
    }

    @PutMapping("/{id}/update")
    CartSummationDto updateCart(@PathVariable Long id, @RequestBody List<CartProductDto> cartProductDtos){
        return CartMapper.mapToCartSummation(cartRepositoryImpl.actualizeCart(id, cartProductDtos));

    }


}