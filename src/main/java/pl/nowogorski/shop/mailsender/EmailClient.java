package pl.nowogorski.shop.mailsender;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailClient {
    @Value("${app.email.sender}")
    private String properties;
    private final Map<String, EmailSender> emailSenderMap;

    public EmailSender getInstance(){
        if(properties.equals("emailSenderLogging")){
            return emailSenderMap.get("emailSenderLogging");
        }
        return emailSenderMap.get("emailSender");
    }

    public EmailSender getInstance(String name){
        if(properties.equals("emailSenderLogging")){
            return emailSenderMap.get("emailSenderLogging");
        }
        return emailSenderMap.get(name);
    }
}
