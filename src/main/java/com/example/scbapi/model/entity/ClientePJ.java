package com.example.scbapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientePJ extends Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cnpj;

    public ClientePJ(String nome, String cnpj, String dataNascimento, Endereco endereco) {
        super(nome, dataNascimento, endereco);
        this.cnpj = cnpj;
    }
}
