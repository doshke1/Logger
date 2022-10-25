package com.example.Logger.service;

import com.example.Logger.model.db.Client;
import com.example.Logger.model.request.RegisterClientRequest;
import com.example.Logger.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterClient {

    private ClientRepository clientRepository;

    @Autowired
    public RegisterClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ResponseEntity execute(RegisterClientRequest request) {
        Optional<Client> client = clientRepository.findClientByUsername(request.getUserName());

        if(client.isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists.");

        client = clientRepository.findClientByPassword(request.getPassword());

        if(client.isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Password already exists.");

        Client newClient = new Client(request.getUserName(), request.getPassword(), request.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.save(newClient));
    }
}
