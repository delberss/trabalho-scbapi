package com.example.scbapi.api.controller;

import com.example.scbapi.api.dto.ClientePFDTO;
import com.example.scbapi.exception.RegraNegocioException;
import com.example.scbapi.model.entity.ClientePF;
import com.example.scbapi.model.entity.Endereco;
import com.example.scbapi.service.ClientePFService;
import com.example.scbapi.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/clientes-pf")
@RequiredArgsConstructor
public class ClientePFController {

    private final ClientePFService service;
    private final EnderecoService enderecoService;

    @GetMapping()
    public ResponseEntity get() {
        List<ClientePF> clientesPF = service.getClientesPF();
        return ResponseEntity.ok(clientesPF.stream().map(ClientePFDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<ClientePF> clientePF = service.getClientePFById(id);
        if (!clientePF.isPresent()) {
            return new ResponseEntity("ClientePF não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(clientePF.map(ClientePFDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(ClientePFDTO dto) {
        try {
            ClientePF clientePF = converter(dto);
            Endereco endereco = enderecoService.salvar(clientePF.getEndereco());
            clientePF.setEndereco(endereco);
            clientePF = service.salvar(clientePF);
            return new ResponseEntity(clientePF, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, ClientePFDTO dto) {
        if (!service.getClientePFById(id).isPresent()) {
            return new ResponseEntity("ClientePF não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            ClientePF clientePF = converter(dto);
            clientePF.setId(id);
            Endereco endereco = enderecoService.salvar(clientePF.getEndereco());
            clientePF.setEndereco(endereco);
            service.salvar(clientePF);
            return ResponseEntity.ok(clientePF);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<ClientePF> clientePF = service.getClientePFById(id);
        if (!clientePF.isPresent()) {
            return new ResponseEntity("ClientePF não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(clientePF.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ClientePF converter(ClientePFDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        ClientePF clientePF = modelMapper.map(dto, ClientePF.class);
        Endereco endereco = modelMapper.map(dto, Endereco.class);
        clientePF.setEndereco(endereco);
        // condições
        return clientePF;
    }
}
