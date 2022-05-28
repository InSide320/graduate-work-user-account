package com.example.sender;

import com.example.exception.ValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application-mail.properties")
public record MailSenderService(JavaMailSender mailSender) {
    @Value("${spring.mail.username}")
    private static String username;

    public void send(String emailTo, String subject, String message) {
        if (emailTo.isBlank()) {
            throw new ValidationException("EmailTo is blank");
        }
        if (subject.isBlank()) {
            throw new ValidationException("Subject is blank");
        }
        if (message.isBlank()) {
            throw new ValidationException("Message is blank");
        }
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(username);
        simpleMailMessage.setTo(emailTo);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        mailSender.send(simpleMailMessage);
    }
}
