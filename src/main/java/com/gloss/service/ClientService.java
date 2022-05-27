package com.gloss.service;

import com.gloss.model.Client;
import com.gloss.repository.ClientCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientCrudRepository clientCrudRepository;


    // Dodaj uzytkownika do bazy
    public Client addClient(String clientName) {
        Client client = Client.builder()
                .clientName(clientName)
                .build();
        return clientCrudRepository.save(client);
    }

    public Optional<Client> getClient(int id) { return clientCrudRepository.findById(id); }


}
