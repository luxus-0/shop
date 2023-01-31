package pl.nowogorski.shop.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.nowogorski.shop.order.dto.OrderDto;
import pl.nowogorski.shop.payment.PaymentService;
import pl.nowogorski.shop.shipment.ShipmentImpl;

@RestController
class OrderController {

    private final OrderImpl orderImpl;
    private final ShipmentImpl shipmentImpl;
    private final PaymentService paymentService;

    OrderController(OrderImpl orderImpl, ShipmentImpl shipmentImpl, PaymentService paymentService) {
        this.orderImpl = orderImpl;
        this.shipmentImpl = shipmentImpl;
        this.paymentService = paymentService;
    }

    @PostMapping("/orders/")
    OrderSummary placeOrder(@RequestBody OrderDto customerDto){
        return orderImpl.placeOrder(customerDto);
    }

    @GetMapping("/orders/initData")
    InitOrder initData() {
        return InitOrder.builder()
                .shipment(shipmentImpl.readShipments())
                .payments(paymentService.readPayments())
                .build();
    }
}
