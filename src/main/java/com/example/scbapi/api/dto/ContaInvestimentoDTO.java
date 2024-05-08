package com.example.scbapi.api.dto;

import com.example.scbapi.model.entity.ContaInvestimento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaInvestimentoDTO {

    private Long id;
    private int numero;
    private double saldo;
    private double investimento;

    public static ContaInvestimentoDTO create(ContaInvestimento contaInvestimento) {
        ModelMapper modelMapper = new ModelMapper();
        ContaInvestimentoDTO dto = modelMapper.map(contaInvestimento, ContaInvestimentoDTO.class);
        return dto;
    }
}
