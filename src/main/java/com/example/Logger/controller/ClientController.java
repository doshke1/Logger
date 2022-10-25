package com.example.Logger.controller;

import com.example.Logger.model.db.Client;
import com.example.Logger.model.request.LoginClientRequest;
import com.example.Logger.model.request.RegisterClientRequest;
import com.example.Logger.service.LoginClient;
import com.example.Logger.service.RegisterClient;
import com.example.Logger.validator.RegisterClientRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private RegisterClientRequestValidator validator;
    private RegisterClient registerClient;
    private LoginClient loginClient;

    @Autowired
    public ClientController(RegisterClientRequestValidator validator, RegisterClient registerClient, LoginClient loginClient) {
        this.validator = validator;
        this.registerClient = registerClient;
        this.loginClient = loginClient;
    }

    @PostMapping("register")

    public ResponseEntity registerClient(@RequestBody RegisterClientRequest request) {
        List<String> validationMessages = validator.validate(request);
        if(!validationMessages.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationMessages);

        return registerClient.execute(request);
    }

    @PostMapping("login")

    public ResponseEntity login(@RequestBody LoginClientRequest request) {
        List<String> validationMessages = validator.validateUsernameAndPassword(request.getUsername(), request.getPassword());
        if(!validationMessages.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationMessages);
        Optional<Client> client = loginClient.execute(request);
        if(client.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(UUID.randomUUID());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(request);
    }


}
