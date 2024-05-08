package com.example.scbapi.api.dto;

import com.example.scbapi.model.entity.ClientePF;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientePFDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public static ClientePFDTO create(ClientePF clientePF) {
        ModelMapper modelMapper = new ModelMapper();
        ClientePFDTO dto = modelMapper.map(clientePF, ClientePFDTO.class);
        dto.logradouro = clientePF.getEndereco().getLogradouro();
        dto.numero = clientePF.getEndereco().getNumero();
        dto.complemento = clientePF.getEndereco().getComplemento();
        dto.cidade = clientePF.getEndereco().getCidade();
        dto.uf = clientePF.getEndereco().getUf();
        dto.cep = clientePF.getEndereco().getCep();
        return dto;
    }
}
