package pl.nowogorski.shop.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.nowogorski.shop.admin.cart.Cart;
import pl.nowogorski.shop.admin.cart.CartItem;
import pl.nowogorski.shop.admin.cart.CartItemRepository;
import pl.nowogorski.shop.admin.cart.CartRepository;
import pl.nowogorski.shop.customer.CustomerDto;
import pl.nowogorski.shop.shipment.Shipment;
import pl.nowogorski.shop.shipment.ShipmentRepository;

import java.math.BigDecimal;
import java.util.List;

import static java.time.LocalDateTime.now;

@Service
class OrderImpl {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartItemRepository cartItemRepository;
    private final ShipmentRepository shipmentRepository;

    OrderImpl(OrderRepository orderRepository, CartRepository cartRepository, OrderRowRepository orderRowRepository, CartItemRepository cartItemRepository, ShipmentRepository shipmentRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.orderRowRepository = orderRowRepository;
        this.cartItemRepository = cartItemRepository;
        this.shipmentRepository = shipmentRepository;
    }

    @Transactional
    public OrderSummary placeOrder(CustomerDto customer) {

        Cart cart = cartRepository.findById(customer.getCartId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(customer.getShipmentId()).orElseThrow();

        Order order = Order.builder()
                .customerId(customer.getId())
                .placeDate(now())
                .orderStatus(OrderStatus.NEW)
                .grossAmount(calculateGrossAmount(cart.getItems(), shipment))
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
