package pl.nowogorski.shop.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.nowogorski.shop.admin.cart.Cart;
import pl.nowogorski.shop.admin.cart.CartItem;
import pl.nowogorski.shop.admin.cart.CartItemRepository;
import pl.nowogorski.shop.admin.cart.CartRepository;

import java.math.BigDecimal;
import java.util.List;

import static java.time.LocalDateTime.now;

@Service
class OrderImpl {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartItemRepository cartItemRepository;

    OrderImpl(OrderRepository orderRepository, CartRepository cartRepository, OrderRowRepository orderRowRepository, CartItemRepository cartItemRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.orderRowRepository = orderRowRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Transactional
    public OrderSummary placeOrder(Cart cart, Long customerId) {
        Long cartItemId = getCartItemId(cart);

        Cart searchCart = cartRepository.findById(cart.getId()).orElseThrow();
        CartItem searchCartItem = cartItemRepository.findById(cartItemId).orElseThrow();

        Order order = Order.builder()
                .customerId(customerId)
                .placeDate(now())
                .orderStatus(OrderStatus.NEW)
                .grossAmount(calculateGrossAmount(cart.getItems()))
                .build();

        Order newOrder = orderRepository.save(order);
        saveOrderRows(cart, newOrder.getId());

        cartItemRepository.deleteByCartId(searchCartItem.getId());
        cartRepository.deleteCardById(searchCart.getId());

        return OrderSummary.builder()
                .id(newOrder.getId())
                .placeDate(newOrder.getPlaceDate())
                .status(newOrder.getOrderStatus())
                .grossAmount(newOrder.getGrossAmount())
                .build();
    }

    private static Long getCartItemId(Cart cart) {
        return cart.getItems()
                .stream()
                .map(CartItem::getId)
                .findAny()
                .orElseThrow();
    }

    private BigDecimal calculateGrossAmount(List<CartItem> items) {
        return items.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private void saveOrderRows(Cart cart, Long id) {
       cart.getItems().stream()
                .map(cartItem -> OrderRow.builder()
                        .quantity(cartItem.getQuantity())
                        .productId(cartItem.getProduct().getId())
                        .price(cartItem.getProduct().getPrice())
                        .orderId(id)
                        .build()
                )
               .findAny()
               .ifPresent(orderRowRepository::save);
    }
}
