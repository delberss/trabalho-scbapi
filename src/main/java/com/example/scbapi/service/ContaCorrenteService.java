package com.example.scbapi.service;

import com.example.scbapi.exception.RegraNegocioException;
import com.example.scbapi.model.entity.ContaCorrente;
import com.example.scbapi.model.repository.ContaCorrenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ContaCorrenteService {
    private ContaCorrenteRepository repository;

    public ContaCorrenteService(ContaCorrenteRepository repository) {
        this.repository = repository;
    }

    public List<ContaCorrente> getContasCorrente() {
        return repository.findAll();
    }

    public Optional<ContaCorrente> getContaCorrenteById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public ContaCorrente salvar(ContaCorrente contaCorrente) {
        validar(contaCorrente);
        return repository.save(contaCorrente);
    }

    @Transactional
    public void excluir(ContaCorrente contaCorrente) {
        Objects.requireNonNull(contaCorrente.getId());
        repository.delete(contaCorrente);
    }

    public void validar(ContaCorrente contaCorrente) {
        if (contaCorrente.getNumero() == 0) {
            throw new RegraNegocioException("Número inválido");
        }
    }
}
