package com.email.envio.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmailAwsDTOTest {

    @Test
    void shouldSerializeEmailAwsDto(){
        EmailAwsDTO emailDTO = new EmailAwsDTO();
        emailDTO.setRecipient("davidhenriquesilva.si@gmail.com");
        emailDTO.setRecipientName("David-O");
        emailDTO.setSender("davidhenriquesilva.si@gmail.com");
        emailDTO.setSubject("Meu consagrado com o OCI com profile");
        emailDTO.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque sed lorem iaculis, condimentum augue ut");
        Assertions.assertEquals("{\"recipient\":\"davidhenriquesilva.si@gmail.com\",\"recipientName\":\"David-O\",\"sender\":\"davidhenriquesilva.si@gmail.com\",\"subject\":\"Meu consagrado com o OCI com profile\",\"content\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque sed lorem iaculis, condimentum augue ut\"}", emailDTO.toJson());
    }
}