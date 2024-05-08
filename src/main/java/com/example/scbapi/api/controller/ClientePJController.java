package com.example.scbapi.api.controller;

import com.example.scbapi.api.dto.ClientePJDTO;
import com.example.scbapi.exception.RegraNegocioException;
import com.example.scbapi.model.entity.ClientePJ;
import com.example.scbapi.model.entity.Endereco;
import com.example.scbapi.service.EnderecoService;
import com.example.scbapi.service.ClientePJService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/clientes-pj")
@RequiredArgsConstructor
public class ClientePJController {

    private final ClientePJService service;
    private final EnderecoService enderecoService;

    @GetMapping()
    public ResponseEntity get() {
        List<ClientePJ> clientesPJ = service.getClientesPJ();
        return ResponseEntity.ok(clientesPJ.stream().map(ClientePJDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<ClientePJ> clientePJ = service.getClientePJById(id);
        if (!clientePJ.isPresent()) {
            return new ResponseEntity("ClientePJ não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(clientePJ.map(ClientePJDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(ClientePJDTO dto) {
        try {
            ClientePJ clientePJ = converter(dto);
            Endereco endereco = enderecoService.salvar(clientePJ.getEndereco());
            clientePJ.setEndereco(endereco);
            clientePJ = service.salvar(clientePJ);
            return new ResponseEntity(clientePJ, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, ClientePJDTO dto) {
        if (!service.getClientePJById(id).isPresent()) {
            return new ResponseEntity("ClientePJ não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            ClientePJ clientePJ = converter(dto);
            clientePJ.setId(id);
            Endereco endereco = enderecoService.salvar(clientePJ.getEndereco());
            clientePJ.setEndereco(endereco);
            service.salvar(clientePJ);
            return ResponseEntity.ok(clientePJ);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<ClientePJ> clientePJ = service.getClientePJById(id);
        if (!clientePJ.isPresent()) {
            return new ResponseEntity("ClientePJ não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(clientePJ.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ClientePJ converter(ClientePJDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        ClientePJ clientePJ = modelMapper.map(dto, ClientePJ.class);
        Endereco endereco = modelMapper.map(dto, Endereco.class);
        clientePJ.setEndereco(endereco);
       // condições
        return clientePJ;
    }
}
