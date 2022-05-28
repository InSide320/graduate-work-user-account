package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class JavaMailSenderConfiguration {

    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.port}")
    private Integer port;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String smtp;
    @Value("${spring.mail.properties.mail.debug}")
    private String debug;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String starttls;

    @Bean
    public JavaMailSender sender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties properties = mailSender.getJavaMailProperties();
        properties.setProperty("mail.smtp.auth", smtp);
        properties.setProperty("mail.smtp.starttls.enable", starttls);
        properties.setProperty("mail.debug", debug);
        properties.setProperty("mail.smtp.ssl.trust", host);

        return mailSender;
    }
}
