package com.example.scbapi.api.dto;

import com.example.scbapi.model.entity.Cliente;
import com.example.scbapi.model.entity.ContaCorrente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaCorrenteDTO {

    private Long id;
    private Integer numero;
    private Cliente cliente;
    private Long idCliente;


    public static ContaCorrenteDTO create(ContaCorrente contaCorrente) {
        ModelMapper modelMapper = new ModelMapper();
        ContaCorrenteDTO dto = modelMapper.map(contaCorrente, ContaCorrenteDTO.class);
        return dto;
    }

}