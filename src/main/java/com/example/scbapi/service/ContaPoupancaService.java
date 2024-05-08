package com.example.scbapi.service;

import com.example.scbapi.exception.RegraNegocioException;
import com.example.scbapi.model.entity.ContaPoupanca;
import com.example.scbapi.model.repository.ContaPoupancaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ContaPoupancaService {
    private ContaPoupancaRepository repository;

    public ContaPoupancaService(ContaPoupancaRepository repository){
        this.repository = repository;
    }

    public List<ContaPoupanca> getContasPoupanca(){
        return repository.findAll();
    }

    public Optional<ContaPoupanca> getContaPoupancaById(Long id){
        return repository.findById(id);
    }

    @Transactional
    public ContaPoupanca salvar(ContaPoupanca contaPoupanca) {
        validar(contaPoupanca);
        return repository.save(contaPoupanca);
    }

    @Transactional
    public void excluir(ContaPoupanca contaPoupanca) {
        Objects.requireNonNull(contaPoupanca.getId());
        repository.delete(contaPoupanca);
    }

    public void validar(ContaPoupanca contaPoupanca) {
        if (contaPoupanca.getNumero() == 0) {
            throw new RegraNegocioException("Número inválido");
        }
    }

}
