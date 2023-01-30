package pl.nowogorski.shop.mailsender;

public interface EmailSender {
    void send(String to, String subject, String text);
}
