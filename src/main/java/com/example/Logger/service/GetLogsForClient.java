package com.example.Logger.service;

import com.example.Logger.model.db.Client;
import com.example.Logger.repository.ClientRepository;
import com.example.Logger.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetLogsForClient {

    private LogRepository logRepository;
    private ClientRepository clientRepository;

    @Autowired
    public GetLogsForClient(LogRepository logRepository, ClientRepository clientRepository) {
        this.logRepository = logRepository;
        this.clientRepository = clientRepository;
    }

    public ResponseEntity execute(String username) {
        Optional<Client> optional = clientRepository.findClientByUsername(username);
        if(!optional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no client with username: " + username);
        }

        Long logCount = logRepository.countAllByClientId(optional.get().getClientId());
        return ResponseEntity.status(HttpStatus.OK).body(logCount);
    }
}
