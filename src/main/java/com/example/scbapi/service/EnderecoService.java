package com.example.scbapi.service;

import com.example.scbapi.model.entity.Endereco;
import com.example.scbapi.model.repository.EnderecoRepository;

import java.util.List;
import java.util.Optional;

public class EnderecoService {
    private EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository){
        this.repository = repository;
    }

    public List<Endereco> getEndereco(){
        return repository.findAll();
    }

    public Optional<Endereco> getEnderecoById(Long id){
        return repository.findById(id);
    }

}
