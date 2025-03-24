package com.email.envio.dto;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class EmailAwsDTO implements Serializable {

    private  static final long serialVersionUID = 1L;

    @Size(max = 45)
    private String recipient;

    @Size(max = 60)
    private String recipientName;

    @Size(max = 45)
    private String sender;

    @Size(max = 120)
    private String subject;

    @Size(max = 256)
    private String content;

    public @Size(max = 45) String getRecipient() {
        return recipient;
    }

    public void setRecipient(@Size(max = 45) String recipient) {
        this.recipient = recipient;
    }

    public @Size(max = 60) String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(@Size(max = 60) String recipientName) {
        this.recipientName = recipientName;
    }

    public @Size(max = 45) String getSender() {
        return sender;
    }

    public void setSender(@Size(max = 45) String sender) {
        this.sender = sender;
    }

    public @Size(max = 120) String getSubject() {
        return subject;
    }

    public void setSubject(@Size(max = 120) String subject) {
        this.subject = subject;
    }

    public @Size(max = 256) String getContent() {
        return content;
    }

    public void setContent(@Size(max = 256) String content) {
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
