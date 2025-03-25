package com.email.envio.service;

import com.email.envio.dto.EmailDTO;
import com.email.envio.dto.EmailRecordDTO;
import com.email.envio.validator.ObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailService {

    @Value("${mail.integracao}")
    String integracao;

    @Autowired
    Map<String, IEmailSenderStrategy> envioEmail;

    public void sendMail(EmailRecordDTO emailDTO) {
        IEmailSenderStrategy sender = envioEmail.getOrDefault(integracao.toLowerCase(), envioEmail.get("oci"));
        sender.sendEmail(emailDTO);
    }

}