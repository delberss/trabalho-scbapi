package com.example.scbapi.service;


import com.example.scbapi.model.entity.ClientePF;
import com.example.scbapi.model.repository.ClientePFRepository;

import java.util.List;
import java.util.Optional;

public class ClientePFService {
    private ClientePFRepository repository;

    public ClientePFService(ClientePFRepository repository){
        this.repository = repository;
    }

    public List<ClientePF> getClientePF(){
        return repository.findAll();
    }

    public Optional<ClientePF> getClientePFById(Long id){
        return repository.findById(id);
    }

}
