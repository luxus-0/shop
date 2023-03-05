package pl.nowogorski.shop.mailsender;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailClient {
    @Value("${app.email.sender}")
    private String emailProperties;
    private final Map<String, EmailSender> emailSenderMap;

    public EmailSender getInstance(){
        if(emailProperties.equals("fakeEmailService")){
            return emailSenderMap.get("fakeEmailService");
        }
        return emailSenderMap.get("mailService");
    }

    public EmailSender getInstance(String beanName){
        if(emailProperties.equals("fakeEmailService")){
            return emailSenderMap.get("fakeEmailService");
        }
        return emailSenderMap.get(beanName);
    }
}
