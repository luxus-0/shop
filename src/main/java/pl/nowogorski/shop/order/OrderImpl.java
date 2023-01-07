package pl.nowogorski.shop.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.nowogorski.shop.admin.cart.Cart;
import pl.nowogorski.shop.admin.cart.CartItem;
import pl.nowogorski.shop.admin.cart.CartItemRepository;
import pl.nowogorski.shop.admin.cart.CartRepository;
import pl.nowogorski.shop.payment.Payment;
import pl.nowogorski.shop.payment.PaymentRepository;
import pl.nowogorski.shop.shipment.Shipment;
import pl.nowogorski.shop.shipment.ShipmentRepository;

import java.math.BigDecimal;
import java.util.List;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
class OrderImpl {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartItemRepository cartItemRepository;
    private final ShipmentRepository shipmentRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public OrderSummary placeOrder(OrderCustomerDto customer) {

        Cart cart = cartRepository.findById(customer.getCartId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(customer.getShipmentId()).orElseThrow();
        Payment payment = paymentRepository.findById(customer.getPaymentId()).orElseThrow();

        Order order = Order.builder()
                .customerId(customer.getId())
                .placeDate(now())
                .orderStatus(OrderStatus.NEW)
                .grossAmount(calculateGrossAmount(cart.getItems(), shipment))
                .payment(payment)
                .build();

        Order newOrder = orderRepository.save(order);
        saveOrderRows(cart, newOrder.getId(), shipment);

        cartItemRepository.deleteByCartId(customer.getCartId());
        cartRepository.deleteCardById(customer.getCartId());

        return OrderSummary.builder()
                .id(newOrder.getId())
                .placeDate(newOrder.getPlaceDate())
                .status(newOrder.getOrderStatus())
                .grossAmount(newOrder.getGrossAmount())
                .payment(payment)
                .build();
    }

    private BigDecimal calculateGrossAmount(List<CartItem> items, Shipment shipment) {
        return items.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .add(shipment.getPrice());
    }

    private void saveOrderRows(Cart cart, Long orderId, Shipment shipment) {
       List<OrderRow> savedProductRows = saveProductRows(cart, orderId, shipment);
      savedProductRows.stream().findAny().ifPresent(orderRowRepository::save);
    }

    private static List<OrderRow> saveProductRows(Cart cart, Long orderId, Shipment shipment) {
        return cart.getItems().stream()
                .map(cartItem -> OrderRow.builder()
                        .quantity(cartItem.getQuantity())
                        .productId(cartItem.getProduct().getId())
                        .price(cartItem.getProduct().getPrice())
                        .shipmentId(shipment.getId())
                        .orderId(orderId)
                        .build()).toList();
    }
}
