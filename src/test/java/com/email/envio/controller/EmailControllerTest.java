package com.email.envio.controller;

import com.email.envio.dto.EmailDTO;
import com.email.envio.service.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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

        BDDMockito.doNothing().when(service).sendMail(any(EmailDTO.class));

        ResultActions response = mockMvc.perform(post("/enviar")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(emailDTO.toJson()));
         response.andExpect(status().isNoContent());
        BDDMockito.verify(service, BDDMockito.times(1)).sendMail(any(EmailDTO.class));
    }


    @Test
    void shouldNotValidateRequestBody() throws Exception {
        ResultActions response = mockMvc.perform(post("/enviar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"));
        response.andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("{\"detail\":\"invalid field\",\"additionalDetails\":{\"sender\":\"sender must have a value\",\"subject\":\"subject must have a value\",\"recipient\":\"recepient must have a value\",\"recipientName\":\"recipientName must have a value\",\"content\":\"content must have a value\"}}"));
    }
}