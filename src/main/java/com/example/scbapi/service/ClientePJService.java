package com.example.scbapi.service;

import com.example.scbapi.model.entity.ClientePJ;
import com.example.scbapi.model.repository.ClientePJRepository;

import java.util.List;
import java.util.Optional;

public class ClientePJService {
    private ClientePJRepository repository;

    public ClientePJService(ClientePJRepository repository){
        this.repository = repository;
    }

    public List<ClientePJ> getClientePJ(){
        return repository.findAll();
    }

    public Optional<ClientePJ> getClientePJById(Long id){
        return repository.findById(id);
    }

}
