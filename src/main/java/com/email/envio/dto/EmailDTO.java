package com.email.envio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

@Component
public class EmailDTO implements Serializable {


    private  static final long serialVersionUID = 1L;

    @NotNull(message = "recepient must have a value")
    private String recipient;

    @NotNull(message = "recipientName must have a value")
    private String recipientName;

    @NotNull(message = "sender must have a value")
    private String sender;

    @NotNull(message = "subject must have a value")
    private String subject;

    @NotNull(message = "content must have a value")
    private String content;

    public EmailDTO(String recipient, String recipientName, String sender, String subject, String content) {
        this.recipient = recipient;
        this.recipientName = recipientName;
        this.sender = sender;
        this.subject = subject;
        this.content = content;
    }

    public EmailDTO (){}

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
