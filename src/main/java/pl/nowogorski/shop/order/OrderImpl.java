package pl.nowogorski.shop.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.nowogorski.shop.admin.cart.Cart;
import pl.nowogorski.shop.admin.cart.CartItemRepository;
import pl.nowogorski.shop.admin.cart.CartRepository;
import pl.nowogorski.shop.customer.Customer;
import pl.nowogorski.shop.mailsender.EmailClient;
import pl.nowogorski.shop.mailsender.EmailSenderImpl;
import pl.nowogorski.shop.payment.Payment;
import pl.nowogorski.shop.payment.PaymentRepository;
import pl.nowogorski.shop.shipment.Shipment;
import pl.nowogorski.shop.shipment.ShipmentRepository;

import java.time.format.DateTimeFormatter;

import static pl.nowogorski.shop.order.mapper.OrderMapper.*;

@Service
@RequiredArgsConstructor
@Log4j2
class OrderImpl {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartItemRepository cartItemRepository;
    private final ShipmentRepository shipmentRepository;
    private final PaymentRepository paymentRepository;
    private final EmailClient emailClient;

    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto) {
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(orderDto.getShipmentId()).orElseThrow();
        Payment payment = paymentRepository.findById(orderDto.getPaymentId()).orElseThrow();
        Order order = createNewOrder(orderDto, cart, shipment, payment);
        Order newOrder = orderRepository.save(order);
        saveOrderRows(cart, newOrder.getId(), shipment);
        clearOrderCart(orderDto);
        String toEmail = sendToCustomerEmail(order);
        log.info("order has been placed");
        emailClient.getInstance().send(toEmail, "Your order has been accepted", createEmailMessage(order));
        return createOrderSummary(payment, newOrder);
    }

    private String sendToCustomerEmail(Order order) {
        return order.getCustomers()
                .stream()
                .map(Customer::getEmail)
                .findAny()
                .orElseThrow();
    }

    private void sendConfirmEmail(){

    }

    private void clearOrderCart(OrderDto customer) {
        cartItemRepository.deleteByCartId(customer.getCartId());
        cartRepository.deleteCardById(customer.getCartId());
    }

    private String createEmailMessage(Order order){
        return "Your order with id: " + order.getId() +
                "\nDate order: " + order.getPlaceDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                "\nPrice: " +order.getGrossAmount() + " PLN " +
                "\n\n" +
                "\nPayment: " + order.getPayment().getName() +
                (order.getPayment().getNote() != null ? "\n" + order.getPayment().getNote() : "") +
                "\n\n Thank you for shopping, best regards: ";
    }

    private void saveOrderRows(Cart cart, Long orderId, Shipment shipment) {
        saveProductRows(cart, orderId);
        saveShipmentRow(orderId, shipment);
    }

    private void saveShipmentRow(Long orderId, Shipment shipment) {
        orderRowRepository.save(createOrderRow(orderId, shipment));
    }

    private void saveProductRows(Cart cart, Long orderId) {
        cart.getItems().stream()
                .map(cartItem -> createOrderRowWithQuantity(orderId, cartItem))
                .peek(orderRowRepository::save)
                .findAny()
                .orElseThrow();
    }
}
