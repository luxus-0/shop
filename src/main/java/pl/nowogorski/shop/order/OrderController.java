package pl.nowogorski.shop.order;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.nowogorski.shop.admin.cart.Cart;
import pl.nowogorski.shop.customer.Customer;
import pl.nowogorski.shop.customer.CustomerDto;

@RestController
class OrderController {

    private final OrderImpl orderImpl;

    OrderController(OrderImpl orderImpl) {
        this.orderImpl = orderImpl;
    }

    @PostMapping("/orders/")
    OrderSummary placeOrder(@RequestBody CustomerDto customerDto){
        return orderImpl.placeOrder(customerDto);
    }
}
