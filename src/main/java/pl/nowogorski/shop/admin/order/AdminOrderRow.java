package pl.nowogorski.shop.admin.order;

import jakarta.persistence.*;
import lombok.Getter;
import pl.nowogorski.shop.admin.product.AdminProduct;
import pl.nowogorski.shop.shipment.Shipment;

import java.math.BigDecimal;

@Entity
@Table(name = "order_row")
@Getter
public class AdminOrderRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    @OneToOne
    @JoinColumn(name = "productId")
    private AdminProduct adminProduct;

    private int quantity;

    private BigDecimal price;

    @OneToOne
    @JoinColumn(name = "shipmentId")
    private Shipment shipment;
}
