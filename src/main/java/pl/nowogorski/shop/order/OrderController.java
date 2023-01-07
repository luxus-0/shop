package pl.nowogorski.shop.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.nowogorski.shop.admin.cart.Cart;

@RestController
class OrderController {

    private OrderImpl orderImpl;

    @PostMapping("/orders/{customerId}")
    OrderSummary placeOrder(@RequestBody Cart cart, @PathVariable Long customerId){
        return orderImpl.placeOrder(cart, customerId);
    }

    @GetMapping("orders/{id}")
    InitOrder initData(@PathVariable Long id){
        return null;
    }
}
