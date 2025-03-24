package com.email.envio.service;

import com.email.envio.converter.DtoConverter;
import com.email.envio.dto.EmailAwsDTO;
import com.email.envio.dto.EmailDTO;
import com.email.envio.exceptions.CustomFieldValidationException;
import com.email.envio.exceptions.ErrorResponse;
import com.email.envio.validator.ObjectValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component("aws")
public class EnvioEmailAws  implements  IEmailSenderStrategy{


    @Autowired
    @Qualifier("awsMailSender")
    JavaMailSender mailSender;

    private  ObjectValidator validator = new ObjectValidator();

    @Override
    public void sendEmail(EmailDTO emailDTO) {
        EmailAwsDTO awsDTO = new DtoConverter().awsConverter(emailDTO);
        var violations  = validator.validate(awsDTO);
        if (!violations.isEmpty()) {
            throw new CustomFieldValidationException(ErrorResponse.INVALID_FIELD,violations);
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(awsDTO.getSender());
        message.setTo(awsDTO.getRecipient());
        message.setSubject(awsDTO.getSubject());
        message.setText(awsDTO.getContent());
        System.out.println("Email AWS: " + awsDTO.toJson());
        mailSender.send(message);
    }
}
