package com.example.Logger.service;

import com.example.Logger.model.db.Client;
import com.example.Logger.model.request.LoginClientRequest;
import com.example.Logger.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginClient {

    private ClientRepository clientRepository;

    @Autowired
    public LoginClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<Client> execute(LoginClientRequest request) {
        Optional<Client> client = clientRepository.findClientByUsernameAndPassword(request.getUsername(), request.getPassword());
        return client;
    }
}
