package com.email.envio.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailDtoTest {

    @Test
    void shouldSerializeEmailDto(){
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setRecipient("davidhenriquesilva.si@gmail.com");
        emailDTO.setRecipientName("David-O");
        emailDTO.setSender("davidhenriquesilva.si@gmail.com");
        emailDTO.setSubject("Meu consagrado com o OCI com profile");
        emailDTO.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque sed lorem iaculis, condimentum augue ut");
        Assertions.assertEquals("{\"recipient\":\"davidhenriquesilva.si@gmail.com\",\"recipientName\":\"David-O\",\"sender\":\"davidhenriquesilva.si@gmail.com\",\"subject\":\"Meu consagrado com o OCI com profile\",\"content\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque sed lorem iaculis, condimentum augue ut\"}", emailDTO.toJson());
    }
}