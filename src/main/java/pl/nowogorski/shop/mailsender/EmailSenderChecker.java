package pl.nowogorski.shop.mailsender;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EmailSenderChecker implements EmailSender {
    @Override
    public void send(String to, String subject, String text) {
        log.info(
                "To: " + to +
                "Subject: " + subject +
                "Text: " + text);
    }
}
