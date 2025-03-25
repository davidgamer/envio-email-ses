package com.email.envio.service;

import com.email.envio.dto.EmailDTO;

public interface IEmailSenderStrategy {

    void sendEmail(EmailDTO emailDTO);
}