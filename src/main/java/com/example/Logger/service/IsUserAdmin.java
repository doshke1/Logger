package com.example.Logger.service;

import com.example.Logger.model.db.Client;
import com.example.Logger.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IsUserAdmin {

    private ClientRepository clientRepository;

    @Autowired
    public IsUserAdmin(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean execute(String username) {
        Optional<Client> optional = clientRepository.findClientByUsername(username);
        if(!optional.isPresent())
            return false;

        return  optional.get().getIsAdmin();
    }
}
