package com.cotacao.seguros.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequestDTO {
    @JsonAlias("email")
    @NotBlank(message = "Usuário é obrigatório")
    private String username;
    @NotBlank(message = "Senha é obrigatória")
    private String password;
}
