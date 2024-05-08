package com.example.scbapi.service;

import com.example.scbapi.exception.RegraNegocioException;
import com.example.scbapi.model.entity.ClientePJ;
import com.example.scbapi.model.repository.ClientePJRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class ClientePJService {
    private ClientePJRepository repository;

    public ClientePJService(ClientePJRepository repository){
        this.repository = repository;
    }

    public List<ClientePJ> getClientesPJ(){
        return repository.findAll();
    }

    public Optional<ClientePJ> getClientePJById(Long id){
        return repository.findById(id);
    }

    @Transactional
    public ClientePJ salvar(ClientePJ clientePJ) {
        validar(clientePJ);
        return repository.save(clientePJ);
    }

    @Transactional
    public void excluir(ClientePJ clientePJ) {
        Objects.requireNonNull(clientePJ.getId());
        repository.delete(clientePJ);
    }

    public void validar(ClientePJ clientePJ) {
        if (clientePJ.getCnpj() == null) {
            throw new RegraNegocioException("CNPJ inválido");
        }
        if (clientePJ.getNome() == null || clientePJ.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
    }

}
