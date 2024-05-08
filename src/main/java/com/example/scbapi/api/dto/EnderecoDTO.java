package com.example.scbapi.api.dto;

import com.example.scbapi.model.entity.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    private Long id;
    private String logradouro;
    private String cidade;
    private String uf;
    private String cep;
    private Integer numero;
    private String complemento;

    public static EnderecoDTO create(Endereco endereco) {
        ModelMapper modelMapper = new ModelMapper();
        EnderecoDTO dto = modelMapper.map(endereco, EnderecoDTO.class);
        return dto;
    }
}
