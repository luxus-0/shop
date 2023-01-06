package pl.nowogorski.shop.admin.cart;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartItems")
class CartItemController {

    private final CartItemImpl cartItemImpl;

    CartItemController(CartItemImpl cartItemImpl) {
        this.cartItemImpl = cartItemImpl;
    }

    @DeleteMapping("/{id}")
    void deleteCartItem(@PathVariable Long id){
        cartItemImpl.deleteCartItem(id);
    }

    @GetMapping("/count/{cartId}")
    Long countItemInCart(@PathVariable Long cartId){
        return cartItemImpl.countItemInCart(cartId);
    }
}
