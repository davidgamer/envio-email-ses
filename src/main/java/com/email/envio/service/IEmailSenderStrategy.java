package com.email.envio.service;

import com.email.envio.dto.EmailDTO;
import com.email.envio.dto.EmailRecordDTO;

public interface IEmailSenderStrategy {

    void sendEmail(EmailRecordDTO emailDTO);
}