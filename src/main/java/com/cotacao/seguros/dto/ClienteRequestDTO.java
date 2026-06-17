package com.cotacao.seguros.dto;

import lombok.Data;

@Data
public class ClienteRequestDTO {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
}
