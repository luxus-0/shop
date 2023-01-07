package pl.nowogorski.shop.shipment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.nowogorski.shop.order.InitOrder;

@RestController
@RequestMapping("/orders")
class ShipmentController {

    private final ShipmentImpl shipmentImpl;

    ShipmentController(ShipmentImpl shipmentImpl) {
        this.shipmentImpl = shipmentImpl;
    }

    @GetMapping("/shipments")
    InitOrder loadOrderShipments(){
        return InitOrder.builder()
                .shipment(shipmentImpl.readShipments())
                .build();
    }

    @GetMapping("/shipment/{id}")
    InitOrder loadOrderShipment(@PathVariable Long id){
        return InitOrder.builder()
                .shipment(shipmentImpl.readShipments(id))
                .build();
    }
}
