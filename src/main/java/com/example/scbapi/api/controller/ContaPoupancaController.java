package com.example.scbapi.api.controller;

import com.example.scbapi.api.dto.ContaPoupancaDTO;
import com.example.scbapi.exception.RegraNegocioException;
import com.example.scbapi.model.entity.ContaPoupanca;
import com.example.scbapi.service.ContaPoupancaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/contas-poupanca")
@RequiredArgsConstructor
public class ContaPoupancaController {

    private final ContaPoupancaService service;

    @GetMapping()
    public ResponseEntity get() {
        List<ContaPoupanca> contasPoupanca = service.getContasPoupanca();
        return ResponseEntity.ok(contasPoupanca.stream().map(ContaPoupancaDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<ContaPoupanca> contaPoupanca = service.getContaPoupancaById(id);
        if (!contaPoupanca.isPresent()) {
            return new ResponseEntity("ContaPoupança não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(contaPoupanca.map(ContaPoupancaDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(ContaPoupancaDTO dto) {
        try {
            ContaPoupanca contaPoupanca = converter(dto);
            contaPoupanca = service.salvar(contaPoupanca);
            return new ResponseEntity(contaPoupanca, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, ContaPoupancaDTO dto) {
        if (!service.getContaPoupancaById(id).isPresent()) {
            return new ResponseEntity("ContaPoupança não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            ContaPoupanca contaPoupanca = converter(dto);
            contaPoupanca.setId(id);
            service.salvar(contaPoupanca);
            return ResponseEntity.ok(contaPoupanca);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<ContaPoupanca> contaPoupanca = service.getContaPoupancaById(id);
        if (!contaPoupanca.isPresent()) {
            return new ResponseEntity("ContaPoupança não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(contaPoupanca.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ContaPoupanca converter(ContaPoupancaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        ContaPoupanca contaPoupanca = modelMapper.map(dto, ContaPoupanca.class);
        //condições
        return contaPoupanca;
    }
}