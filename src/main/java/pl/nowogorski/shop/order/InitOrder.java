package pl.nowogorski.shop.order;

import lombok.Builder;
import lombok.Getter;
import pl.nowogorski.shop.shipment.Shipment;

import java.util.List;

@Getter
@Builder
public class InitOrder {
    private List<Shipment> shipment;
}
