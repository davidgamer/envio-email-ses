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
public class EnvioEmailAwsTest {

    @Mock
    private JavaMailSender mailSender;

    @Test
    void shouldHaveValidationIssue(){
        EmailDTO awsDto = new EmailDTO();
        awsDto.setRecipient(RandomStringUtils.random(46, true, true));
        awsDto.setRecipientName(RandomStringUtils.random(61, true, true));
        awsDto.setSender(RandomStringUtils.random(46, true, true));
        awsDto.setSubject(RandomStringUtils.random(122, true, true));
        awsDto.setContent(RandomStringUtils.random(257, true, true));
        EnvioEmailAws envioEmailAws = new EnvioEmailAws();

        CustomFieldValidationException exception =  Assertions.assertThrows(CustomFieldValidationException.class, ()-> envioEmailAws.sendEmail(awsDto));
        Assertions.assertEquals(ErrorResponse.INVALID_FIELD, exception.getMessage());

        HashMap<String,String> expected = new HashMap<>();
        expected.put("recipient","tamanho deve ser entre 0 e 45");
        expected.put("subject","tamanho deve ser entre 0 e 120");
        expected.put("sender","tamanho deve ser entre 0 e 45");
        expected.put("recipientName","tamanho deve ser entre 0 e 60");
        expected.put("content","tamanho deve ser entre 0 e 256");
        Assertions.assertEquals(expected, exception.getDetails());
    }

    @Test
    void shouldSendmail(){
        EmailDTO awsDTO = new EmailDTO();
        awsDTO.setRecipient("davidhenriquesilva.si@gmail.com");
        awsDTO.setRecipientName("David-O");
        awsDTO.setSender("davidhenriquesilva.si@gmail.com");
        awsDTO.setSubject("Meu consagrado com o OCI com profile");
        awsDTO.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque sed lorem iaculis, condimentum augue ut");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(awsDTO.getSender());
        message.setTo(awsDTO.getRecipient());
        message.setSubject(awsDTO.getSubject());
        message.setText(awsDTO.getContent());

        BDDMockito.doNothing().when(mailSender).send(message);
        EnvioEmailAws envioEmailAws = new EnvioEmailAws();
        envioEmailAws.mailSender = mailSender;
        Assertions.assertDoesNotThrow(()-> envioEmailAws.sendEmail(awsDTO));
        BDDMockito.verify(mailSender, BDDMockito.times(1)).send(message);
    }
}