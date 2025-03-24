package com.email.envio.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailOciDTOTest {

    @Test
    void shouldSerializeEmailOciDto(){
        EmailOciDTO emailDTO = new EmailOciDTO();
        emailDTO.setRecipientEmail("davidhenriquesilva.si@gmail.com");
        emailDTO.setRecipientName("David-O");
        emailDTO.setSenderEmail("davidhenriquesilva.si@gmail.com");
        emailDTO.setSubject("Meu consagrado com o OCI com profile");
        emailDTO.setBody("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque sed lorem iaculis, condimentum augue ut");
        Assertions.assertEquals("{\"recipientEmail\":\"davidhenriquesilva.si@gmail.com\",\"recipientName\":\"David-O\",\"senderEmail\":\"davidhenriquesilva.si@gmail.com\",\"subject\":\"Meu consagrado com o OCI com profile\",\"body\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque sed lorem iaculis, condimentum augue ut\"}", emailDTO.toJson());
    }
}
