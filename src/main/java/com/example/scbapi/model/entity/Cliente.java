package com.example.scbapi.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public abstract class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String dataNascimento;

    @OneToOne
    private Endereco endereco;


    public Cliente(String nome, String dataNascimento, Endereco endereco) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

}
