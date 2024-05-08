package com.example.scbapi.service;

import com.example.scbapi.model.entity.ContaInvestimento;
import com.example.scbapi.model.entity.ContaPoupanca;
import com.example.scbapi.model.repository.ContaInvestimentoRepository;
import com.example.scbapi.model.repository.ContaPoupancaRepository;

import java.util.List;
import java.util.Optional;

public class ContaPoupancaService {
    private ContaPoupancaRepository repository;

    public ContaPoupancaService(ContaPoupancaRepository repository){
        this.repository = repository;
    }

    public List<ContaPoupanca> getContaPoupanca(){
        return repository.findAll();
    }

    public Optional<ContaPoupanca> getContaPoupancaById(Long id){
        return repository.findById(id);
    }

}
