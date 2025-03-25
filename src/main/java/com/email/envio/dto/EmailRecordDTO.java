package com.email.envio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record EmailRecordDTO(
        @Email
        String recipient,
        @NotNull
        String recipientName,
        @Email
        String sender,
        @NotNull
        String subject,
        @NotNull
        String content) {
}