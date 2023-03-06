package pl.nowogorski.shop.admin.order.dto;

import lombok.Builder;
import pl.nowogorski.shop.admin.order.AdminOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record AdminOrderDto(Long id, LocalDate placeDate, AdminOrderStatus adminOrderStatus, BigDecimal grossValue) {
}
