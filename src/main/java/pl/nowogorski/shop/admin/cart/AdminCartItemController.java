package pl.nowogorski.shop.admin.cart;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartItems")
class AdminCartItemController {

    private final AdminCartItemImpl adminCartItemImpl;

    AdminCartItemController(AdminCartItemImpl adminCartItemImpl) {
        this.adminCartItemImpl = adminCartItemImpl;
    }

    @DeleteMapping("/{id}")
    void deleteCartItem(@PathVariable Long id){
        adminCartItemImpl.deleteCartItem(id);
    }

    @GetMapping("/count/{cartId}")
    Long countItemInCart(@PathVariable Long cartId){
        return adminCartItemImpl.countItemInCart(cartId);
    }
}
