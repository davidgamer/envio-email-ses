package com.email.envio.service;

import com.email.envio.converter.DtoConverter;
import com.email.envio.dto.EmailDTO;
import com.email.envio.dto.EmailOciDTO;
import com.email.envio.exceptions.CustomFieldValidationException;
import com.email.envio.exceptions.ErrorResponse;
import com.email.envio.validator.ObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component("oci")
@Primary
public class EnvioEmailOci implements IEmailSenderStrategy {


    @Autowired
    @Qualifier("ociMailSender")
     JavaMailSender mailSender;

    private  ObjectValidator validator = new ObjectValidator();

    @Override
    public void sendEmail(EmailDTO emailDTO) {
        EmailOciDTO ociDTO = new DtoConverter().ociConverter(emailDTO);
        var violations = validator.validate(ociDTO);
        if (!violations.isEmpty()) {
            throw new CustomFieldValidationException(ErrorResponse.INVALID_FIELD,violations);
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(ociDTO.getSenderEmail());
        message.setTo(ociDTO.getRecipientEmail());
        message.setSubject(ociDTO.getSubject());
        message.setText(ociDTO.getBody());
        System.out.println("Email OCI: " + ociDTO.toJson());
        mailSender.send(message);
    }

}
