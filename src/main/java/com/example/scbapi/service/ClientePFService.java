package com.example.scbapi.service;


import com.example.scbapi.exception.RegraNegocioException;
import com.example.scbapi.model.entity.ClientePF;
import com.example.scbapi.model.repository.ClientePFRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientePFService {
    private ClientePFRepository repository;

    public ClientePFService(ClientePFRepository repository){
        this.repository = repository;
    }

    public List<ClientePF> getClientesPF(){
        return repository.findAll();
    }

    public Optional<ClientePF> getClientePFById(Long id){
        return repository.findById(id);
    }

    @Transactional
    public ClientePF salvar(ClientePF clientePF) {
        validar(clientePF);
        return repository.save(clientePF);
    }

    @Transactional
    public void excluir(ClientePF clientePF) {
        Objects.requireNonNull(clientePF.getId());
        repository.delete(clientePF);
    }

    public void validar(ClientePF clientePF) {
        if (clientePF.getCpf() == null) {
            throw new RegraNegocioException("CPF inválido");
        }
        if (clientePF.getNome() == null || clientePF.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
        if (clientePF.getDataNascimento() == null|| clientePF.getDataNascimento().trim().equals("")) {
            throw new RegraNegocioException("Data de Nascimento inválida");
        }
    }

}
