package pl.nowogorski.shop.admin.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
class CartController {

    private CartService cartService;

    CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{id}")
    CartSummaryDto readCart(@PathVariable Long id){
        return CartMapper.mapToCartSummary(cartService.readCart(id));
    }

    @PutMapping("/{id}")
    CartSummaryDto addProductToCart(@PathVariable Long id, @RequestBody CartProductDto cartProductDto) {
        return CartMapper.mapToCartSummary(cartService.addProductToCart(id, cartProductDto));
    }

    @PutMapping("/{id}/update")
    CartSummaryDto updateCart(@PathVariable Long id, @RequestBody List<CartProductDto> cartProductDtos){
        return CartMapper.mapToCartSummary(cartService.actualizeCart(id, cartProductDtos));

    }


}
