package com.email.envio.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class EmailOciDTO implements Serializable {

    private  static final long serialVersionUID = 1L;

    @Size(max = 40)
    private String recipientEmail;

    @Size(max = 50)
    private String recipientName;

    @Size(max = 40)
    private String senderEmail;

    @Size(max = 100)
    private String subject;

    @Size(max = 250)
    private String body;

    public @Size(max = 40) String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(@Size(max = 40) String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public @Size(max = 50) String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(@Size(max = 50) String recipientName) {
        this.recipientName = recipientName;
    }

    public @Size(max = 40) String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(@Size(max = 40) String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public @Size(max = 100) String getSubject() {
        return subject;
    }

    public void setSubject(@Size(max = 100) String subject) {
        this.subject = subject;
    }

    public @Size(max = 250) String getBody() {
        return body;
    }

    public void setBody(@Size(max = 250) String body) {
        this.body = body;
    }

    public String toJson() {
        try{
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);

        } catch (JsonProcessingException e){
            throw new IllegalArgumentException("Json convert error");
        }
    }
}