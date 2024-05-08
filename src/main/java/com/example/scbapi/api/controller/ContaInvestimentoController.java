package com.example.scbapi.api.controller;

import com.example.scbapi.api.dto.ContaInvestimentoDTO;
import com.example.scbapi.exception.RegraNegocioException;
import com.example.scbapi.model.entity.ContaInvestimento;
import com.example.scbapi.service.ContaInvestimentoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/contas-investimento")
@RequiredArgsConstructor
public class ContaInvestimentoController {
    private final ContaInvestimentoService service;

    @GetMapping()
    public ResponseEntity get() {
        List<ContaInvestimento> contasInvestimento = service.getContasInvestimento();
        return ResponseEntity.ok(contasInvestimento.stream().map(ContaInvestimentoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<ContaInvestimento> contaInvestimento = service.getContaInvestimentoById(id);
        if (!contaInvestimento.isPresent()) {
            return new ResponseEntity("ContaInvestimento não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(contaInvestimento.map(ContaInvestimentoDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(ContaInvestimentoDTO dto) {
        try {
            ContaInvestimento contaInvestimento = converter(dto);
            contaInvestimento = service.salvar(contaInvestimento);
            return new ResponseEntity(contaInvestimento, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, ContaInvestimentoDTO dto) {
        if (!service.getContaInvestimentoById(id).isPresent()) {
            return new ResponseEntity("ContaInvestimento não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            ContaInvestimento contaInvestimento = converter(dto);
            contaInvestimento.setId(id);
            service.salvar(contaInvestimento);
            return ResponseEntity.ok(contaInvestimento);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<ContaInvestimento> contaInvestimento = service.getContaInvestimentoById(id);
        if (!contaInvestimento.isPresent()) {
            return new ResponseEntity("ContaInvestimento não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(contaInvestimento.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ContaInvestimento converter(ContaInvestimentoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        ContaInvestimento contaInvestimento = modelMapper.map(dto, ContaInvestimento.class);
        //condições
        return contaInvestimento;
    }

}
