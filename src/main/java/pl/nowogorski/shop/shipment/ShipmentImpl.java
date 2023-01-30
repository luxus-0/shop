package pl.nowogorski.shop.shipment;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentImpl {

    private final ShipmentRepository shipmentRepository;

    ShipmentImpl(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public List<Shipment> readShipments() {
        return shipmentRepository.findAll();
    }

    public List<Shipment> readShipments(Long id) {
        return shipmentRepository.findById(id).map(List::of).orElseThrow();
    }
}
