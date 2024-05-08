package com.example.scbapi.service;

import com.example.scbapi.exception.RegraNegocioException;
import com.example.scbapi.model.entity.ContaInvestimento;
import com.example.scbapi.model.repository.ContaInvestimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ContaInvestimentoService {
    private ContaInvestimentoRepository repository;

    public ContaInvestimentoService(ContaInvestimentoRepository repository){
        this.repository = repository;
    }

    public List<ContaInvestimento> getContasInvestimento(){
        // Retorna todas as contas de investimento do repositório
        return repository.findAll();
    }

    public Optional<ContaInvestimento> getContaInvestimentoById(Long id){
        // Retorna a conta de investimento pelo ID fornecido
        return repository.findById(id);
    }

    @Transactional
    public ContaInvestimento salvar(ContaInvestimento contaInvestimento) {
        validar(contaInvestimento);
        return repository.save(contaInvestimento);
    }

    @Transactional
    public void excluir(ContaInvestimento aluno) {
        Objects.requireNonNull(aluno.getId());
        repository.delete(aluno);
    }

    public void validar(ContaInvestimento contaInvestimento) {
        if (contaInvestimento.getNumero() == 0) {
            throw new RegraNegocioException("Número inválido");
        }
    }
}
