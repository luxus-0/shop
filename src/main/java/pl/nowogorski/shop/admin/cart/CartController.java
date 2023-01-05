package pl.nowogorski.shop.admin.cart;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
class CartController {

    private final CartRepositoryImpl cartRepositoryImpl;

    CartController(CartRepositoryImpl cartRepositoryImpl) {
        this.cartRepositoryImpl = cartRepositoryImpl;
    }

    @GetMapping("/{id}")
    Cart readCart(@PathVariable Long id){
        return cartRepositoryImpl.readCart(id);
    }

    @PutMapping("/{id}")
    Cart addProductToCart(@PathVariable Long id, CartProductDto cartProductDto){
        return cartRepositoryImpl.addProductToCart(id, cartProductDto);
    }
}
