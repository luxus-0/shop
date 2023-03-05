package pl.nowogorski.shop.infrastructure.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import pl.nowogorski.shop.mailsender.EmailSender;
import pl.nowogorski.shop.mailsender.FakeEmailService;
import pl.nowogorski.shop.mailsender.EmailSenderImpl;

@Configuration
public class EmailConfig {

    @Bean
    @ConditionalOnProperty(name = "app.email.sender", matchIfMissing = true, havingValue = "mailService")
    public EmailSender emailSender(JavaMailSender javaMailSender){
        return new EmailSenderImpl(javaMailSender);
    }

    @Bean
    @ConditionalOnProperty(name = "app.email.sender", havingValue = "fakeEmailService")
    public EmailSender fakeEmailService(){
        return new FakeEmailService();
    }
}
