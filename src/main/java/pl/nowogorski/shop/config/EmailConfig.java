package pl.nowogorski.shop.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import pl.nowogorski.shop.mailsender.EmailSender;
import pl.nowogorski.shop.mailsender.EmailSenderLogging;
import pl.nowogorski.shop.mailsender.EmailSenderImpl;

public class EmailConfig {

    @Bean
    @ConditionalOnProperty(name = "app.email.sender", matchIfMissing = true, havingValue = "mailService")
    public EmailSender emailSender(JavaMailSender javaMailSender){
        return new EmailSenderImpl(javaMailSender);
    }

    @Bean
    @ConditionalOnProperty(name = "app.email.sender", havingValue = "mailServiceLog")
    public EmailSender emailSenderChecker(){
        return new EmailSenderLogging();
    }
}
