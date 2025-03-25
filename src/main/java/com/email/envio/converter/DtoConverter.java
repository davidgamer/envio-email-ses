package com.email.envio.converter;

import com.email.envio.dto.EmailAwsDTO;
import com.email.envio.dto.EmailDTO;
import com.email.envio.dto.EmailOciDTO;
import com.email.envio.dto.EmailRecordDTO;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {

    public EmailAwsDTO awsConverter(EmailRecordDTO emailDTO){
        EmailAwsDTO awsDTO = new EmailAwsDTO();
        awsDTO.setContent(emailDTO.content());
        awsDTO.setSender(emailDTO.sender());
        awsDTO.setRecipient(emailDTO.recipient());
        awsDTO.setRecipientName(emailDTO.recipientName());
        awsDTO.setSubject(emailDTO.subject());

        return awsDTO;
    }

    public EmailOciDTO ociConverter(EmailRecordDTO emailDTO){
        EmailOciDTO ociDTO = new EmailOciDTO();
        ociDTO.setBody(emailDTO.content());
        ociDTO.setSenderEmail(emailDTO.sender());
        ociDTO.setRecipientEmail(emailDTO.recipient());
        ociDTO.setRecipientName(emailDTO.recipientName());
        ociDTO.setSubject(emailDTO.subject());

        return ociDTO;
    }
}