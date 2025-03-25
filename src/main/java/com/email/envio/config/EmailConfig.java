package com.email.envio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Value("${mail.integracao}")
    private String integracao;

    @Autowired
    private AwsEmailConfig awsEmailConfig;

    @Autowired
    private OciEmailConfig ociEmailConfig;

    @Bean(name = "awsMailSender")
    public JavaMailSender awsMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setPort(awsEmailConfig.getPort());
        mailSender.setHost(awsEmailConfig.getHost());
        mailSender.setUsername(awsEmailConfig.getUsername());
        mailSender.setPassword(awsEmailConfig.getPassword());
        setProperties(mailSender);

        return mailSender;
    }

    @Bean(name = "ociMailSender")
    public JavaMailSender ociMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setPort(ociEmailConfig.getPort());
        mailSender.setHost(ociEmailConfig.getHost());
        mailSender.setUsername(ociEmailConfig.getUsername());
        mailSender.setPassword(ociEmailConfig.getPassword());
        setProperties(mailSender);

        return mailSender;
    }

    private void setProperties(JavaMailSenderImpl mailSender) {
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");
    }
}
