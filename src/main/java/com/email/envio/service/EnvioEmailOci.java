package com.email.envio.service;

import com.email.envio.converter.DtoConverter;
import com.email.envio.dto.EmailAwsDTO;
import com.email.envio.dto.EmailDTO;
import com.email.envio.dto.EmailOciDTO;
import com.email.envio.validator.ObjectValidator;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class EnvioEmailOci implements IEmailSenderStrategy{

    private final ObjectValidator validator;

    public EnvioEmailOci(ObjectValidator validator) {
        this.validator = validator;
    }


    @Override
    public SimpleMailMessage sendEmail(EmailDTO emailDTO) {
        try {

            EmailOciDTO ociDTO = new DtoConverter().ociConverter(emailDTO);
            var violations  = validator.validate(ociDTO);
            if(!violations.isEmpty()){
                throw new ValidationException(String.join(" | ", violations));
            }

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(ociDTO.getSenderEmail());
            message.setTo(ociDTO.getRecipientEmail());
            message.setSubject(ociDTO.getSubject());
            message.setText(ociDTO.getBody());
            System.out.println("Email AWS: " + ociDTO.toJson());

            return  message;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
