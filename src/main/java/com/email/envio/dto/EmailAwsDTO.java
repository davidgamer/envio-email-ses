package com.email.envio.dto;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class EmailAwsDTO implements Serializable {

    private  static final long serialVersionUID = 1L;


    @Size(max = 45)
    private String recepient;

    @Size(max = 60)
    private String recipientName;

    @Size(max = 45)
    private String sender;

    @Size(max = 120)
    private String subject;

    @Size(max = 256)
    private String content;


    public EmailAwsDTO() {
    }

    public EmailAwsDTO(String recepient, String recipientName, String sender, String subject, String content) {
        this.recepient = recepient;
        this.recipientName = recipientName;
        this.sender = sender;
        this.subject = subject;
        this.content = content;
    }

    public @Size(max = 45) String getRecepient() {
        return recepient;
    }

    public void setRecepient(@Size(max = 45) String recepient) {
        this.recepient = recepient;
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
