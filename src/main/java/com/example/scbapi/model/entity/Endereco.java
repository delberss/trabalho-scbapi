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
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private String cidade;
    private String uf;
    private String cep;
    private Integer numero;
    private String complemento;

    public Endereco(String logradouro, String cidade, String uf, String cep, Integer numero, String complemento) {
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
    }

}
