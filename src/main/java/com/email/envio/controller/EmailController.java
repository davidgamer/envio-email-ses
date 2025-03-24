package com.email.envio.controller;

import com.email.envio.dto.EmailDTO;
import com.email.envio.service.EmailService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EmailController {

    @Autowired
    private EmailService service;

    @PostMapping("/enviar")
    public ResponseEntity<EmailDTO> enviar(@Valid @RequestBody EmailDTO email){
        service.sendMail(email);
        System.out.println("\n Dados Recebidos: " + email.toJson());
        return ResponseEntity.noContent().build();
    }
}