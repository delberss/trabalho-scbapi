package com.example.scbapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public abstract class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;


    protected double saldo;

    public Conta(int numero, Cliente cliente) {
        this.numero = numero;
        this.saldo = 0;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public abstract void sacar(double valor);


}
