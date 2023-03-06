package pl.nowogorski.shop.order;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.nowogorski.shop.customer.Customer;
import pl.nowogorski.shop.payment.Payment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate placeDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToMany
    @JoinColumn(name = "orderId")
    private List<OrderRow> orderRows;
    private BigDecimal grossAmount;
    @OneToMany
    @JoinColumn(name = "orderId")
    private Set<Customer> customers;
    @OneToOne
    private Payment payment;
}
