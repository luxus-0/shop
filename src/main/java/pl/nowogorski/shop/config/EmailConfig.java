package pl.nowogorski.shop.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import pl.nowogorski.shop.mailsender.EmailSender;
import pl.nowogorski.shop.mailsender.EmailSenderChecker;
import pl.nowogorski.shop.mailsender.EmailSenderImpl;

public class EmailConfig {

    @Bean
    @ConditionalOnProperty(value = "app.email.sender", matchIfMissing = true, havingValue = "mailService")
    public EmailSender emailSender(JavaMailSender javaMailSender){
        return new EmailSenderImpl(javaMailSender);
    }

    @Bean
    @ConditionalOnProperty(value = "app.email.sender", havingValue = "mailServiceLog")
    public EmailSender emailSenderChecker(){
        return new EmailSenderChecker();
    }
}
