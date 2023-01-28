package pl.nowogorski.shop.mailsender;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderImpl {

    private final JavaMailSender mailSender;
    public void send(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("luxus0@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
