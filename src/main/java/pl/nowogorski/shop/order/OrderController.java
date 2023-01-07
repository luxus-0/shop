package pl.nowogorski.shop.order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class OrderController {

    private final OrderImpl orderImpl;

    OrderController(OrderImpl orderImpl) {
        this.orderImpl = orderImpl;
    }

    @PostMapping("/orders/")
    OrderSummary placeOrder(@RequestBody OrderCustomerDto customerDto){
        return orderImpl.placeOrder(customerDto);
    }
}
