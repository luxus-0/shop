package pl.nowogorski.shop.order;

import org.springframework.stereotype.Service;
import pl.nowogorski.shop.admin.cart.AdminCart;
import pl.nowogorski.shop.admin.cart.AdminCartItem;
import pl.nowogorski.shop.customer.Customer;
import pl.nowogorski.shop.order.dto.OrderDto;
import pl.nowogorski.shop.payment.Payment;
import pl.nowogorski.shop.payment.PaymentType;
import pl.nowogorski.shop.product.Product;
import pl.nowogorski.shop.shipment.Shipment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.time.LocalDateTime.now;

@Service
class OrderMapper {

    static Order createNewOrder(OrderDto orderDto, AdminCart adminCart, Shipment shipment, Payment payment) {
        Customer customer = createCustomer(orderDto);
        Set<Customer> customers = Set.of(customer);
        return Order.builder()
                .customers(customers)
                .placeDate(adminCart.getCreated())
                .orderStatus(OrderStatus.NEW)
                .grossAmount(calculateGrossAmount(adminCart.getItems(), shipment))
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

    static BigDecimal calculateGrossAmount(List<AdminCartItem> items, Shipment shipment) {
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

    static OrderRow mapToOrderRowWithQuantity(Long orderId, AdminCartItem adminCartItem) {
        return OrderRow.builder()
                .quantity(adminCartItem.getQuantity())
                .productId(adminCartItem.getProduct().getId())
                .price(adminCartItem.getProduct().getPrice())
                .orderId(orderId)
                .build();
    }

    static Optional<Payment> createPayment() {
        return Optional.of(Payment.builder()
                .id(1L)
                .name("test payment")
                .type(PaymentType.TRANSFER)
                .checkPayment(true)
                .build());
    }

    static Optional<Shipment> createShipment() {
        return Optional.of(Shipment.builder()
                .id(3L)
                .price(new BigDecimal("22.22"))
                .build());
    }

    static Optional<AdminCart> createCart() {
        return Optional.of(AdminCart.builder()
                .id(1L)
                .created(LocalDate.now())
                .items(createItems())
                .build());
    }

    private static List<AdminCartItem> createItems() {
        return List.of(createCartItem1(), createCartItem2());
    }

    private static AdminCartItem createCartItem2() {
        return AdminCartItem.builder()
                .id(2L)
                .cartId(1L)
                .quantity(1)
                .product(Product.builder()
                        .id(2L)
                        .price(new BigDecimal("11.34"))
                        .build())
                .build();
    }

    private static AdminCartItem createCartItem1() {
        return AdminCartItem.builder()
                .id(1L)
                .cartId(1L)
                .quantity(1)
                .product(createProduct())
                .build();
    }

    private static Product createProduct() {
        return Product.builder()
                .id(1L)
                .price(new BigDecimal("11.34"))
                .build();
    }

    static OrderDto createOrderDto() {
        return OrderDto.builder()
                .firstName("Łukasz")
                .lastName("Lelek")
                .street("Zilencja")
                .zipCode("34-560")
                .city("Gwarenda")
                .email("bisek@o2.as")
                .phone("+345678433")
                .cartId(1L)
                .shipmentId(2L)
                .paymentId(3L)
                .build();
    }
}
