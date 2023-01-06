package pl.nowogorski.shop.admin.cart;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
