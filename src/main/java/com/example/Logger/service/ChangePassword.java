package com.example.Logger.service;

import com.example.Logger.model.db.Client;
import com.example.Logger.model.request.ChangePasswordRequest;
import com.example.Logger.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChangePassword {

    private ClientRepository clientRepository;

    @Autowired
    public ChangePassword(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ResponseEntity execute(ChangePasswordRequest request) {
        Optional<Client> optional = clientRepository.findClientByPassword(request.getOldPassword());
        if(optional.isPresent()){
            Client client = optional.get();
            client.setPassword(request.getNewPassword());
            client = clientRepository.save(client);
            return ResponseEntity.status(HttpStatus.OK).body("New password is: " +client.getPassword());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with password "+ request.getOldPassword() + " not found.");
    }
}
