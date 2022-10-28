package com.example.Logger.service;

import com.example.Logger.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountLogsForClient {

    private ClientRepository clientRepository;

    @Autowired
    public CountLogsForClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Integer execute(String username) {
        return 0;
    }
}
