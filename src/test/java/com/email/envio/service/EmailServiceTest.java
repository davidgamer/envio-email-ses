package com.email.envio.service;

import com.email.envio.dto.EmailDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    EnvioEmailOci envioEmailOci;

    @Mock
    EnvioEmailAws envioEmailAws;

    @Test
    void shouldCallSendMailAWS(){
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setRecipient("davidhenriquesilva.si@gmail.com");
        emailDTO.setRecipientName("David-O");
        emailDTO.setSender("davidhenriquesilva.si@gmail.com");
        emailDTO.setSubject("Meu consagrado com o OCI com profile");
        emailDTO.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque sed lorem iaculis, condimentum augue ut");

        Map<String, IEmailSenderStrategy> envioEmail  = Map.of(
                "oci", envioEmailOci,
                "aws", envioEmailAws
        );

        EmailService service = new EmailService();
        service.integracao = "AWS";
        service.envioEmail = envioEmail;
        Assertions.assertDoesNotThrow(()-> service.sendMail(emailDTO));
        BDDMockito.verify(envioEmailAws, BDDMockito.times(1)).sendEmail(emailDTO);
        BDDMockito.verifyNoInteractions(envioEmailOci);
    }


    @Test
    void shouldSendMailOCI(){

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setRecipient("davidhenriquesilva.si@gmail.com");
        emailDTO.setRecipientName("David-O");
        emailDTO.setSender("davidhenriquesilva.si@gmail.com");
        emailDTO.setSubject("Meu consagrado com o OCI com profile");
        emailDTO.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque sed lorem iaculis, condimentum augue ut");

        Map<String, IEmailSenderStrategy> envioEmail  = Map.of(
                "oci", envioEmailOci,
                "aws", envioEmailAws
        );

        EmailService service = new EmailService();
        service.integracao = "OCI";
        service.envioEmail = envioEmail;
        Assertions.assertDoesNotThrow(()-> service.sendMail(emailDTO));
        BDDMockito.verify(envioEmailOci, BDDMockito.times(1)).sendEmail(emailDTO);
        BDDMockito.verifyNoInteractions(envioEmailAws);

    }


    @Test
    void shouldSendMailDefault(){

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setRecipient("davidhenriquesilva.si@gmail.com");
        emailDTO.setRecipientName("David-O");
        emailDTO.setSender("davidhenriquesilva.si@gmail.com");
        emailDTO.setSubject("Meu consagrado com o OCI com profile");
        emailDTO.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque sed lorem iaculis, condimentum augue ut");

        Map<String, IEmailSenderStrategy> envioEmail  = Map.of(
                "oci", envioEmailOci,
                "aws", envioEmailAws
        );

        EmailService service = new EmailService();
        service.integracao = "ZZZ";
        service.envioEmail = envioEmail;
        Assertions.assertDoesNotThrow(()-> service.sendMail(emailDTO));
        BDDMockito.verify(envioEmailOci, BDDMockito.times(1)).sendEmail(emailDTO);
        BDDMockito.verifyNoInteractions(envioEmailAws);
    }
}
