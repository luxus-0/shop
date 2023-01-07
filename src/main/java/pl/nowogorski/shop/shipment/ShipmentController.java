package pl.nowogorski.shop.shipment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.nowogorski.shop.order.InitOrder;
import pl.nowogorski.shop.payment.PaymentService;

@RestController
class ShipmentController {

    private final ShipmentImpl shipmentImpl;
    private final PaymentService paymentService;

    ShipmentController(ShipmentImpl shipmentImpl, PaymentService paymentService) {
        this.shipmentImpl = shipmentImpl;
        this.paymentService = paymentService;
    }

    @GetMapping("/shipments/payments")
    InitOrder loadShipmentPayments(){
        return InitOrder.builder()
                .shipment(shipmentImpl.readShipments())
                .payments(paymentService.readPayments())
                .build();
    }

    @GetMapping("/shipment/{id}")
    InitOrder loadOrderShipment(@PathVariable Long id){
        return InitOrder.builder()
                .shipment(shipmentImpl.readShipments(id))
                .build();
    }
}
