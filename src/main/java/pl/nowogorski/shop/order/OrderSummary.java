package pl.nowogorski.shop.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.nowogorski.shop.payment.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
class OrderSummary {
    private Long id;
    private LocalDateTime placeDate;
    private OrderStatus status;
    private BigDecimal grossAmount;
    private Payment payment;
}
