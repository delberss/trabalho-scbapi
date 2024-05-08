package com.example.scbapi.api.dto;

import com.example.scbapi.model.entity.ContaPoupanca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaPoupancaDTO {

    private Long id;
    private int numero;
    private double saldo;

    public static ContaPoupancaDTO create(ContaPoupanca contaPoupanca) {
        ModelMapper modelMapper = new ModelMapper();
        ContaPoupancaDTO dto = modelMapper.map(contaPoupanca, ContaPoupancaDTO.class);
        return dto;
    }
}
