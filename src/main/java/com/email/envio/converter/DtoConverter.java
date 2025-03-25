package com.email.envio.converter;

import com.email.envio.dto.EmailAwsDTO;
import com.email.envio.dto.EmailDTO;
import com.email.envio.dto.EmailOciDTO;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {

    public EmailAwsDTO awsConverter(EmailDTO emailDTO){
        EmailAwsDTO awsDTO = new EmailAwsDTO();
        awsDTO.setContent(emailDTO.getContent());
        awsDTO.setSender(emailDTO.getSender());
        awsDTO.setRecipient(emailDTO.getRecipient());
        awsDTO.setRecipientName(emailDTO.getRecipientName());
        awsDTO.setSubject(emailDTO.getSubject());

        return awsDTO;
    }

    public EmailOciDTO ociConverter(EmailDTO emailDTO){
        EmailOciDTO ociDTO = new EmailOciDTO();
        ociDTO.setBody(emailDTO.getContent());
        ociDTO.setSenderEmail(emailDTO.getSender());
        ociDTO.setRecipientEmail(emailDTO.getRecipient());
        ociDTO.setRecipientName(emailDTO.getRecipientName());
        ociDTO.setSubject(emailDTO.getSubject());

        return ociDTO;
    }
}