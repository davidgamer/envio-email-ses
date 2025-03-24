package com.email.envio.controller;

import com.email.envio.dto.EmailDTO;
import com.email.envio.service.EmailService;
import com.email.envio.service.EnvioEmailAws;
import com.email.envio.service.IEmailSenderStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(controllers = EmailController.class)
@ExtendWith(MockitoExtension.class)
public class EmailControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    EmailService service;

    @Test
    void shouldGetSendEmailAndGiveNoContentResponse() throws Exception{
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setRecipient("davidhenriquesilva.si@gmail.com");
        emailDTO.setRecipientName("David-O");
        emailDTO.setSender("davidhenriquesilva.si@gmail.com");
        emailDTO.setSubject("Meu consagrado com o OCI com profile");
        emailDTO.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque sed lorem iaculis, condimentum augue ut");

        ResultActions response = mockMvc.perform(post("/enviar")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(emailDTO.toJson()));
         response.andExpect(MockMvcResultMatchers.status().isNoContent());
         verify(service).sendMail(any(EmailDTO.class));
    }

    @Test
    void shouldValidateRequestBody(){

    }
}
