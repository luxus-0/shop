package pl.nowogorski.shop.mailsender;

import org.springframework.stereotype.Service;

@Service
public class EmailSenderWeb implements EmailSender{

    @Override
    public void send(String to, String subject, String text) {
        throw new RuntimeException("Not implemented yet");
    }
}
