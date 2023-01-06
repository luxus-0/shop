package pl.nowogorski.shop.order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class OrderController {

    private OrderImpl orderImpl;

    @PostMapping("/orders")
    OrderSummary placeOrder(@RequestBody OrderDto orderDto){
        return orderImpl.placeOrder(orderDto);
    }
}
