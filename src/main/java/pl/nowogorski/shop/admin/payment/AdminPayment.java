package pl.nowogorski.shop.admin.payment;

import jakarta.persistence.*;
import lombok.Getter;
import pl.nowogorski.shop.payment.PaymentType;

@Entity
@Table(name = "payment")
@Getter
public class AdminPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private PaymentType type;
    private boolean defaultPayment;
    private String note;
}
