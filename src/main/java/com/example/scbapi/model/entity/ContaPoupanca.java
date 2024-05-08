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
public class ContaPoupanca extends Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public ContaPoupanca(int numero, Cliente cliente) {
        super(numero, cliente);
    }

    @Override
    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
        }
    }

    public void calcularRendimento() {
        double rendimento = saldo * 0.03; // 3% de rendimento ao mês
        saldo += rendimento;
    }
}

