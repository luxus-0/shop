package pl.nowogorski.shop.mailsender;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailClient {
    private final Map<String, EmailSender> emailSenderMap;

    public EmailSender getInstance(){
        return emailSenderMap.get("emailSender");
    }
}
