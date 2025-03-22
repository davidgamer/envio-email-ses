package com.email.envio.service;

import com.email.envio.converter.DtoConverter;
import com.email.envio.dto.EmailAwsDTO;
import com.email.envio.dto.EmailDTO;
import com.email.envio.validator.ObjectValidator;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Set;

@Component
public class EnvioEmailAws  implements  IEmailSenderStrategy{


    private final  ObjectValidator validator;

    public EnvioEmailAws(ObjectValidator validator) {
        this.validator = validator;
    }



    @Override
    public SimpleMailMessage sendEmail(EmailDTO emailDTO) {
        try {

            EmailAwsDTO awsDTO = new DtoConverter().awsConverter(emailDTO);
            var violations  = validator.validate(awsDTO);
            if(!violations.isEmpty()){
                throw new ValidationException(String.join(" | ", violations));
            }

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(awsDTO.getSender());
            message.setTo(awsDTO.getRecepient());
            message.setSubject(awsDTO.getSubject());
            message.setText(awsDTO.getContent());
            System.out.println("Email AWS: " + awsDTO.toJson());

            return  message;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
