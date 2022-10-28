package com.example.Logger.repository;

import com.example.Logger.model.db.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientByUsername(String username);

    Optional<Client> findClientByPassword(String password);

    Optional<Client> findClientByUsernameAndPassword(String username, String password);
}
