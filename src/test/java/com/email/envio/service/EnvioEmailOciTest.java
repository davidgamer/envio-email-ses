package com.email.envio.service;

import com.email.envio.dto.EmailDTO;
import com.email.envio.exceptions.CustomFieldValidationException;
import com.email.envio.exceptions.ErrorResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
public class EnvioEmailOciTest {

    @Mock
    private JavaMailSender mailSender;

    @Test
    void shouldHaveValidationIssue(){
        EmailDTO ociDTO = new EmailDTO();
        ociDTO.setRecipient(RandomStringUtils.random(46, true, true));
        ociDTO.setRecipientName(RandomStringUtils.random(61, true, true));
        ociDTO.setSender(RandomStringUtils.random(46, true, true));
        ociDTO.setSubject(RandomStringUtils.random(121, true, true));
        ociDTO.setContent(RandomStringUtils.random(257, true, true));
        EnvioEmailOci envioEmailOci = new EnvioEmailOci();
        CustomFieldValidationException exception =  Assertions.assertThrows(CustomFieldValidationException.class, ()-> envioEmailOci.sendEmail(ociDTO));
        Assertions.assertEquals(ErrorResponse.INVALID_FIELD, exception.getMessage());

        HashMap<String,String> expected = new HashMap<>();
        expected.put("subject","tamanho deve ser entre 0 e 100");
        expected.put("senderEmail","tamanho deve ser entre 0 e 40");
        expected.put("recipientName","tamanho deve ser entre 0 e 50");
        expected.put("body","tamanho deve ser entre 0 e 250");
        expected.put("recipientEmail","tamanho deve ser entre 0 e 40");
        Assertions.assertEquals(expected, exception.getDetails());
    }





    @Test
    void shouldSendmail(){
        EmailDTO ociDTO = new EmailDTO();
        ociDTO.setRecipient("davidhenriquesilva.si@gmail.com");
        ociDTO.setRecipientName("David-O");
        ociDTO.setSender("davidhenriquesilva.si@gmail.com");
        ociDTO.setSubject("Meu consagrado com o OCI com profile");
        ociDTO.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque sed lorem iaculis, condimentum augue ut");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(ociDTO.getSender());
        message.setTo(ociDTO.getRecipient());
        message.setSubject(ociDTO.getSubject());
        message.setText(ociDTO.getContent());

        BDDMockito.doNothing().when(mailSender).send(message);
        EnvioEmailOci envioEmailOci = new EnvioEmailOci();
        envioEmailOci.mailSender = mailSender;
        Assertions.assertDoesNotThrow(()-> envioEmailOci.sendEmail(ociDTO));
        BDDMockito.verify(mailSender, BDDMockito.times(1)).send(message);
    }
}
