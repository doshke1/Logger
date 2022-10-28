package com.example.Logger.service;

import com.example.Logger.model.db.Client;
import com.example.Logger.model.dto.ClientDTO;
import com.example.Logger.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllClients {

    private ClientRepository clientRepository;

    @Autowired
    public GetAllClients(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDTO> execute(){
        List<ClientDTO> dtos = new ArrayList<>();
        List<Client> clients = clientRepository.findAll();
        clients.forEach(client -> dtos.add(new ClientDTO(client.getClientId(), client.getUsername(), client.getEmail(), client.getIsAdmin())));
        return dtos;
    }
}
