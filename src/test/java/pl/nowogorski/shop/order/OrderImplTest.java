package pl.nowogorski.shop.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.nowogorski.shop.admin.cart.CartItemRepository;
import pl.nowogorski.shop.admin.cart.CartRepository;
import pl.nowogorski.shop.mailsender.EmailClient;
import pl.nowogorski.shop.mailsender.EmailSenderLogging;
import pl.nowogorski.shop.order.dto.OrderDto;
import pl.nowogorski.shop.payment.PaymentRepository;
import pl.nowogorski.shop.payment.PaymentType;
import pl.nowogorski.shop.shipment.ShipmentRepository;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pl.nowogorski.shop.order.OrderMapper.*;

@ExtendWith(MockitoExtension.class)
class OrderImplTest {

    @InjectMocks
    private OrderImpl orderImpl;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private ShipmentRepository shipmentRepository;

    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private EmailClient emailSender;
    @Mock
    private OrderRowRepository orderRowRepository;
    @Mock
    private CartItemRepository cartItemRepository;

    @Test
    @DisplayName("should return order summary")
    void shouldReturnPlaceOrder() {
        //given
        OrderDto orderDto = createOrderDto();
        when(cartRepository.findById(any())).thenReturn(createCart());
        when(shipmentRepository.findById(any())).thenReturn(createShipment());
        when(paymentRepository.findById(any())).thenReturn(createPayment());
        when(orderRepository.save(any())).thenAnswer(invocation -> invocation.getArguments()[0]);
        when(emailSender.getInstance()).thenReturn(new EmailSenderLogging());
        //when
        OrderSummary orderSummary = orderImpl.placeOrder(orderDto);
        //then
        assertThat(orderSummary).isNotNull();
        assertThat(orderSummary.getStatus()).isEqualTo(OrderStatus.NEW);
        assertThat(orderSummary.getGrossAmount()).isEqualTo(new BigDecimal("44.90"));
        assertThat(orderSummary.getPayment().getType()).isEqualTo(PaymentType.TRANSFER);
        assertThat(orderSummary.getPayment().getName()).isEqualTo("test payment");
        assertThat(orderSummary.getPayment().getId()).isEqualTo(1L);
    }
}