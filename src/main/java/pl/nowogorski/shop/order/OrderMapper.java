package pl.nowogorski.shop.order;

import org.springframework.stereotype.Service;
import pl.nowogorski.shop.admin.cart.Cart;
import pl.nowogorski.shop.admin.cart.CartItem;
import pl.nowogorski.shop.customer.Customer;
import pl.nowogorski.shop.order.dto.OrderDto;
import pl.nowogorski.shop.payment.Payment;
import pl.nowogorski.shop.shipment.Shipment;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static java.time.LocalDateTime.now;

@Service
class OrderMapper {
    static Order createNewOrder(OrderDto orderDto, Cart cart, Shipment shipment, Payment payment) {
        Customer customer = createCustomer(orderDto);
        Set<Customer> customers = Set.of(customer);
        return Order.builder()
                .customers(customers)
                .placeDate(now())
                .orderStatus(OrderStatus.NEW)
                .grossAmount(calculateGrossAmount(cart.getItems(), shipment))
                .payment(payment)
                .build();
    }

    private static Customer createCustomer(OrderDto orderDto) {
        return Customer.builder()
                .firstName(orderDto.getFirstName())
                .lastName(orderDto.getLastName())
                .street(orderDto.getStreet())
                .zipCode(orderDto.getZipCode())
                .city(orderDto.getCity())
                .email(orderDto.getEmail())
                .phone(orderDto.getPhone())
                .build();
    }

    static BigDecimal calculateGrossAmount(List<CartItem> items, Shipment shipment) {
        return items.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .add(shipment.getPrice());
    }

    static OrderSummary createOrderSummary(Payment payment, Order newOrder) {
        return OrderSummary.builder()
                .id(newOrder.getId())
                .placeDate(newOrder.getPlaceDate())
                .status(newOrder.getOrderStatus())
                .grossAmount(newOrder.getGrossAmount())
                .payment(payment)
                .build();
    }

    static OrderRow mapToOrderRow(Long orderId, Shipment shipment) {
        return OrderRow.builder()
                .quantity(1)
                .price(shipment.getPrice())
                .shipmentId(shipment.getId())
                .orderId(orderId)
                .build();
    }

    static OrderRow mapToOrderRowWithQuantity(Long orderId, CartItem cartItem) {
        return OrderRow.builder()
                .quantity(cartItem.getQuantity())
                .productId(cartItem.getProduct().getId())
                .price(cartItem.getProduct().getPrice())
                .orderId(orderId)
                .build();
    }
}
