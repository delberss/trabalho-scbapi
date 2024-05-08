package com.example.scbapi.api.dto;

import com.example.scbapi.model.entity.ClientePJ;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientePJDTO {

    private Long id;
    private String nome;
    private String cnpj;
    private String dataNascimento;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public static ClientePJDTO create(ClientePJ clientePJ) {
        ModelMapper modelMapper = new ModelMapper();
        ClientePJDTO dto = modelMapper.map(clientePJ, ClientePJDTO.class);
        dto.logradouro = clientePJ.getEndereco().getLogradouro();
        dto.numero = clientePJ.getEndereco().getNumero();
        dto.complemento = clientePJ.getEndereco().getComplemento();
        dto.cidade = clientePJ.getEndereco().getCidade();
        dto.uf = clientePJ.getEndereco().getUf();
        dto.cep = clientePJ.getEndereco().getCep();
        return dto;
    }
}
