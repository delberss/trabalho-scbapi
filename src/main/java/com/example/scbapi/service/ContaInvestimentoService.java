package com.example.scbapi.service;

import com.example.scbapi.model.entity.ContaInvestimento;
import com.example.scbapi.model.repository.ContaInvestimentoRepository;

import java.util.List;
import java.util.Optional;

public class ContaInvestimentoService {
    private ContaInvestimentoRepository repository;

    public ContaInvestimentoService(ContaInvestimentoRepository repository){
        this.repository = repository;
    }

    public List<ContaInvestimento> getContaInvestimento(){
        return repository.findAll();
    }

    public Optional<ContaInvestimento> getContaInvestimentoById(Long id){
        return repository.findById(id);
    }

}
