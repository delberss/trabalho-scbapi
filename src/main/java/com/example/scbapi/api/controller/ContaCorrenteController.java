package com.example.scbapi.api.controller;

import com.example.scbapi.api.dto.ContaCorrenteDTO;
import com.example.scbapi.exception.RegraNegocioException;
import com.example.scbapi.model.entity.ContaCorrente;
import com.example.scbapi.service.ContaCorrenteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/contas-corrente")
@RequiredArgsConstructor
public class ContaCorrenteController {

    private final ContaCorrenteService service;

    @GetMapping()
    public ResponseEntity get() {
        List<ContaCorrente> contasCorrente = service.getContasCorrente();
        return ResponseEntity.ok(contasCorrente.stream().map(ContaCorrenteDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<ContaCorrente> contaCorrente = service.getContaCorrenteById(id);
        if (!contaCorrente.isPresent()) {
            return new ResponseEntity("ContaCorrente não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(contaCorrente.map(ContaCorrenteDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(ContaCorrenteDTO dto) {
        try {
            ContaCorrente contaCorrente = converter(dto);
            contaCorrente = service.salvar(contaCorrente);
            return new ResponseEntity(contaCorrente, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, ContaCorrenteDTO dto) {
        if (!service.getContaCorrenteById(id).isPresent()) {
            return new ResponseEntity("ContaCorrente não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            ContaCorrente contaCorrente = converter(dto);
            contaCorrente.setId(id);
            service.salvar(contaCorrente);
            return ResponseEntity.ok(contaCorrente);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<ContaCorrente> contaCorrente = service.getContaCorrenteById(id);
        if (!contaCorrente.isPresent()) {
            return new ResponseEntity("ContaCorrente não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(contaCorrente.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ContaCorrente converter(ContaCorrenteDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        ContaCorrente contaCorrente = modelMapper.map(dto, ContaCorrente.class);
        //condições
        return contaCorrente;
    }
}
