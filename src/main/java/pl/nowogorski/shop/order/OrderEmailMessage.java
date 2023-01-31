package pl.nowogorski.shop.order;

import java.time.format.DateTimeFormatter;

class OrderEmailMessage {

    static String createEmailMessage(Order order){
        return "Your order with id: " + order.getId() +
                "\nDate order: " + order.getPlaceDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                "\nPrice: " +order.getGrossAmount() + " PLN " +
                "\n\n" +
                "\nPayment: " + order.getPayment().getName() +
                (order.getPayment().getNote() != null ? "\n" + order.getPayment().getNote() : "") +
                "\n\n Thank you for shopping, best regards: ";
    }
}
