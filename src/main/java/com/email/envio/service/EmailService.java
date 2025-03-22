package com.email.envio.service;

import com.email.envio.dto.EmailDTO;
import com.email.envio.validator.ObjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.integracao}")
    private String integracao;




    private final Map<String, IEmailSenderStrategy> strategyMap = Map.of(
            "OCI", new EnvioEmailOci(new ObjectValidator()),
            "AWS", new EnvioEmailAws(new ObjectValidator())
    );

    public void sendMail(EmailDTO emailDTO) {

        mailSender.send( strategyMap.get(integracao).sendEmail(emailDTO));

    }

}
