package pl.nowogorski.shop.admin.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.nowogorski.shop.admin.payment.AdminPayment;
import pl.nowogorski.shop.customer.Customer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "'order'")
@Getter
@Setter
public class AdminOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate placeDate;

    @Enumerated(EnumType.STRING)
    private AdminOrderStatus adminOrderStatus;

    @OneToMany
    @JoinColumn(name = "orderId")
    private List<AdminOrderRow> orderRows;

    private BigDecimal grossValue;

    @OneToOne
    private Customer customer;

    @OneToOne
    private AdminPayment adminPayment;
}
