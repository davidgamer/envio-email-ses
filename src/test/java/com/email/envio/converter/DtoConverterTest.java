package com.email.envio.converter;

import com.email.envio.dto.EmailAwsDTO;
import com.email.envio.dto.EmailDTO;
import com.email.envio.dto.EmailOciDTO;
import com.email.envio.dto.EmailRecordDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DtoConverterTest {

    @Test
    void shouldConvertEmailDTOToEmailAwsDTO(){

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setRecipient("davidhenriquesilva.si@gmail.com");
        emailDTO.setRecipientName("David-O");
        emailDTO.setSender("davidhenriquesilva.si@gmail.com");
        emailDTO.setSubject("Meu consagrado com o OCI com profile");
        emailDTO.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque sed lorem iaculis, condimentum augue ut");

        EmailAwsDTO awsDTO = new DtoConverter().awsConverter(emailDTO);

        Assertions.assertEquals(emailDTO.getContent(), awsDTO.getContent());
        Assertions.assertEquals(emailDTO.getSender(), awsDTO.getSender());
        Assertions.assertEquals(emailDTO.getRecipient(), awsDTO.getRecipient());
        Assertions.assertEquals(emailDTO.getRecipientName(), awsDTO.getRecipientName());
        Assertions.assertEquals(emailDTO.getSubject(), awsDTO.getSubject());

    }


    @Test
    void shouldConvertEmailDTOToEmailOciDTO(){
        EmailRecordDTO emailDTO = new EmailRecordDTO(
       "davidhenriquesilva.si@gmail.com",
        "David-O",
        "davidhenriquesilva.si@gmail.com",
        "Meu consagrado com o OCI com profile",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque sed lorem iaculis, condimentum augue ut");

        EmailOciDTO ociDTO = new DtoConverter().ociConverter(emailDTO);

        Assertions.assertEquals(emailDTO.content(), ociDTO.getBody());
        Assertions.assertEquals(emailDTO.sender(), ociDTO.getSenderEmail());
        Assertions.assertEquals(emailDTO.recipient(), ociDTO.getRecipientEmail());
        Assertions.assertEquals(emailDTO.recipientName(), ociDTO.getRecipientName());
        Assertions.assertEquals(emailDTO.subject(), ociDTO.getSubject());
    }
}
