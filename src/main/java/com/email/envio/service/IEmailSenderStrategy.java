package com.email.envio.service;

import com.email.envio.dto.EmailDTO;
import org.springframework.mail.SimpleMailMessage;

public interface IEmailSenderStrategy {

    SimpleMailMessage sendEmail(EmailDTO emailDTO);
}
