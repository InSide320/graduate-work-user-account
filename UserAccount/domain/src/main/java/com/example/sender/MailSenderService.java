package com.example.sender;

import com.example.exception.MessageException;
import com.example.exception.ValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;

@Service
@PropertySource("classpath:application-mail.properties")
public record MailSenderService(
        JavaMailSender mailSender,
        MimeMessage mimeMessage) {

    @Value("${spring.mail.username}")
    private static String username;

    public void sendFile(
            String emailTo,
            String subject,
            String message,
            String attachment
    ) throws MessageException {

        if (emailTo.isBlank()) {
            throw new ValidationException("EmailTo is blank");
        }
        if (subject.isBlank()) {
            throw new ValidationException("Subject is blank");
        }
        if (message.isBlank()) {
            throw new ValidationException("Message is blank");
        }
        if (attachment.isBlank()) {
            throw new NullPointerException("File path is null");
        }

        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("dekud2109@gmail.com");
            mimeMessageHelper.setTo(emailTo);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message);

            FileSystemResource fileSystemResource
                    = new FileSystemResource(new File(attachment));
            mimeMessageHelper
                    .addAttachment(
                            Objects.requireNonNull(fileSystemResource.getFilename()),
                            fileSystemResource);
        } catch (MessagingException e) {
            throw new MessageException(e.getMessage(), e);
        }


        mailSender.send(mimeMessage);

    }

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
