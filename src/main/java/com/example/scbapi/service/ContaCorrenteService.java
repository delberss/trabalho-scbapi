package com.example.scbapi.service;

import com.example.scbapi.model.entity.ContaCorrente;
import com.example.scbapi.model.repository.ContaCorrenteRepository;

import java.util.List;
import java.util.Optional;

public class ContaCorrenteService {
    private ContaCorrenteRepository repository;

    public ContaCorrenteService(ContaCorrenteRepository repository){
        this.repository = repository;
    }

    public List<ContaCorrente> getContaCorrente(){
        return repository.findAll();
    }

    public Optional<ContaCorrente> getContaCorrenteById(Long id){
        return repository.findById(id);
    }

}
