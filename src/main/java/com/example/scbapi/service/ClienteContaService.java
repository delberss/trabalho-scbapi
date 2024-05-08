package com.example.scbapi.service;

import com.example.scbapi.model.entity.ClienteConta;
import com.example.scbapi.model.repository.ClienteContaRepository;

import java.util.List;
import java.util.Optional;

public class ClienteContaService {
    private ClienteContaRepository repository;

    public ClienteContaService(ClienteContaRepository repository){
        this.repository = repository;
    }

    public List<ClienteConta> getClienteConta(){
        return repository.findAll();
    }

    public Optional<ClienteConta> getClienteContaById(Long id){
        return repository.findById(id);
    }

}
